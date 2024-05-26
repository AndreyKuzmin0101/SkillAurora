package ru.kpfu.itis.skillshare.mainservice.repository.jpa.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.skillshare.mainservice.model.AnswerEntity;
import ru.kpfu.itis.skillshare.mainservice.model.QuestionEntity;
import ru.kpfu.itis.skillshare.mainservice.dto.QuestionStatus;
import ru.kpfu.itis.skillshare.mainservice.model.TagEntity;
import ru.kpfu.itis.skillshare.mainservice.repository.jpa.QuestionJpaRepository;

import java.util.ArrayList;

import java.util.List;

@Repository
public class QuestionJpaRepositoryImpl implements QuestionJpaRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<QuestionEntity> findQuestionsByFilter(Pageable pageable, String search, Boolean noAnswers,  QuestionStatus status, List<TagEntity> tags) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<QuestionEntity> query = criteriaBuilder.createQuery(QuestionEntity.class);

        Root<QuestionEntity> root = query.from(QuestionEntity.class);
        query.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if (search != null) {
            search = search.toLowerCase();
            Predicate predicateSearch = criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + search + "%");
            predicates.add(predicateSearch);
        }

        if (noAnswers != null && noAnswers) {
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<AnswerEntity> subRoot = subquery.from(AnswerEntity.class);
            subquery.select(criteriaBuilder.count(subRoot));
            subquery.where(criteriaBuilder.equal(subRoot.get("question"), root));
            Predicate noAnswerPredicate = criteriaBuilder.equal(subquery, 0);
            predicates.add(noAnswerPredicate);
        }

        if (tags != null && !tags.isEmpty()) {
            Join<QuestionEntity, TagEntity> tag = root.join("tags", JoinType.LEFT);
            List<Integer> ids = tags.stream().map(TagEntity::getId).toList();
            Predicate tagsPredicate = tag.get("id").in(ids);
            predicates.add(tagsPredicate);
        }

        if (status != null) {
            Predicate statusPredicate = criteriaBuilder.equal(root.get("status"), status);
            predicates.add(statusPredicate);
        }

        Predicate[] arr = new Predicate[predicates.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = predicates.get(i);
        }
        query.where(arr);

        Sort.Order sortOrder = pageable.getSort().get().findFirst().orElse(null);
        if (sortOrder != null) {
            Order order = criteriaBuilder.desc(root.get(sortOrder.getProperty()));
            query.orderBy(order);
        }

        List<QuestionEntity> questions = em.createQuery(query).getResultList();

        List<QuestionEntity> subList;

        int start = (int) pageable.getOffset();
        int end = (Math.min((start + pageable.getPageSize()), questions.size()));
        if (start < end) {
            subList = questions.subList(start, end);
        } else {
            subList = new ArrayList<>();
        }
        return new PageImpl<>(subList, pageable, questions.size());
    }
}
