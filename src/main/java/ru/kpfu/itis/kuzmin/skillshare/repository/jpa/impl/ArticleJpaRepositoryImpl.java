package ru.kpfu.itis.kuzmin.skillshare.repository.jpa.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.kuzmin.skillshare.model.ArticleEntity;
import ru.kpfu.itis.kuzmin.skillshare.model.TagEntity;
import ru.kpfu.itis.kuzmin.skillshare.repository.jpa.ArticleJpaRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class ArticleJpaRepositoryImpl implements ArticleJpaRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    public Page<ArticleEntity> findArticlesByFilter(Pageable pageable, Long ratingThreshold,
                                                    Date from, Date to, List<TagEntity> tags) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<ArticleEntity> query = criteriaBuilder.createQuery(ArticleEntity.class);

        Root<ArticleEntity> root = query.from(ArticleEntity.class);

        Predicate predicateRating = criteriaBuilder.greaterThanOrEqualTo(root.get("rating"), ratingThreshold);
        Predicate predicatePeriod = criteriaBuilder.between(root.get("publicationDate"), from, to);
        Predicate predicateStatus = criteriaBuilder.equal(root.get("moderationStatus"), "confirmed");


        query.select(root).where(predicatePeriod, predicateRating, predicateStatus);

        Sort.Order sortOrder = pageable.getSort().get().findFirst().orElse(null);
        if (sortOrder != null) {
            Order order = criteriaBuilder.desc(root.get(sortOrder.getProperty()));
            query.orderBy(order);
        }

        List<ArticleEntity> articles = em.createQuery(query).getResultList();

        List<ArticleEntity> articlesByTags = new ArrayList<>();
        if (tags == null) tags = new ArrayList<>();
        for (ArticleEntity article : articles) {
            Set<Integer> articleTagIds = article.getTags().stream().map(TagEntity::getId).collect(Collectors.toSet());
            Set<Integer> argumentTagIds = tags.stream().map(TagEntity::getId).collect(Collectors.toSet());
            if (articleTagIds.containsAll(argumentTagIds)) {
                articlesByTags.add(article);
            }
        }

        List<ArticleEntity> subList;

        int start = (int) pageable.getOffset();
        int end = (Math.min((start + pageable.getPageSize()), articlesByTags.size()));
        if (start < end) {
            subList = articlesByTags.subList(start, end);
        } else {
            subList = new ArrayList<>();
        }
        return new PageImpl<>(subList, pageable, articlesByTags.size());
    }
}
