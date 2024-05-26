package ru.kpfu.itis.skillshare.mainservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.skillshare.mainservice.dto.Roles;
import ru.kpfu.itis.skillshare.mainservice.exception.notfound.ArticleNotFoundException;
import ru.kpfu.itis.skillshare.mainservice.model.ArticleEntity;
import ru.kpfu.itis.skillshare.mainservice.model.RatingEntity;
import ru.kpfu.itis.skillshare.mainservice.model.RoleEntity;
import ru.kpfu.itis.skillshare.mainservice.model.UserEntity;
import ru.kpfu.itis.skillshare.mainservice.repository.spring.ArticleSpringRepository;
import ru.kpfu.itis.skillshare.mainservice.repository.spring.RatingSpringRepository;
import ru.kpfu.itis.skillshare.mainservice.repository.spring.RoleSpringRepository;
import ru.kpfu.itis.skillshare.mainservice.repository.spring.UserSpringRepository;
import ru.kpfu.itis.skillshare.mainservice.service.RatingService;
import ru.kpfu.itis.skillshare.mainservice.security.util.SecurityUtil;

import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final ArticleSpringRepository articleSpringRepository;
    private final RatingSpringRepository ratingSpringRepository;
    private final UserSpringRepository userSpringRepository;
    private final RoleSpringRepository roleSpringRepository;

    @Override
    public Long getArticleRating(Long articleId) {
        Optional<ArticleEntity> optionalArticle = articleSpringRepository.findById(articleId);
        if (optionalArticle.isPresent()) {
            return ratingSpringRepository.sumRatingByArticleId(articleId);
        }
        throw new ArticleNotFoundException(articleId);
    }

    @Override
    public Integer getRatingByCurrUserForArticle(Long articleId) {
        Optional<ArticleEntity> optionalArticle = articleSpringRepository.findById(articleId);
        if (optionalArticle.isPresent()) {
            Long userId = SecurityUtil.getIdAuthenticatedUser();
            Optional<RatingEntity> rating = ratingSpringRepository.findByArticleAndUser(articleId, userId);
            if (rating.isPresent()) {
                return rating.get().getRatingValue();
            } else {
                return 0;
            }
        }
        throw new ArticleNotFoundException(articleId);
    }

    @Transactional
    @Override
    public void plusToArticle(Long articleId) {
        Long userId = SecurityUtil.getIdAuthenticatedUser();
        Integer rating = determineRatingChange(userId);
        changeArticleRating(articleId, rating);
    }

    @Transactional
    @Override
    public void minusToArticle(Long articleId) {
        Long userId = SecurityUtil.getIdAuthenticatedUser();
        Integer rating = -determineRatingChange(userId);
        changeArticleRating(articleId, rating);
    }

    @Override
    public void checkCurrentRating(Long userId) {
        UserEntity user = userSpringRepository.findById(userId).get();
        List<String> roles = user.getRoles().stream().map(RoleEntity::getName).toList();
        if (user.getRating() >= 200 && !roles.contains(Roles.AUTHOR.getFullName())) {
            RoleEntity roleAuthor = roleSpringRepository.findByName(Roles.AUTHOR.getFullName()).get();
            user.getRoles().add(roleAuthor);
        } else if (user.getRating() < 200 && roles.contains(Roles.AUTHOR.getFullName())) {
            user.getRoles().removeIf(roleEntity -> roleEntity.getName().equals(Roles.AUTHOR.getFullName()));
        }
    }

    // В зависимости от репутации пользователя или иных причин оценка пользователя
    // может быть более или менее ценной
    private Integer determineRatingChange(Long userId) {
        return 5;
    }

    private void changeArticleRating(Long articleId, Integer rating) {
        Optional<ArticleEntity> optionalArticle = articleSpringRepository.findById(articleId);
        if (optionalArticle.isPresent()) {
            ArticleEntity article = optionalArticle.get();

            Long userId = SecurityUtil.getIdAuthenticatedUser();

            Optional<RatingEntity> optionalRating = ratingSpringRepository.findByArticleAndUser(articleId, userId);

            UserEntity author = article.getAuthor();
            if (optionalRating.isPresent()) {
                RatingEntity ratingEntity = optionalRating.get();
                if ((ratingEntity.getRatingValue() < 0 && rating > 0) ||
                        (ratingEntity.getRatingValue() > 0 && rating < 0)) {
                    author.setRating(author.getRating() + rating * 2);
                }
            } else {
                author.setRating(author.getRating() + rating);
            }
            checkCurrentRating(author.getId());

            if (optionalRating.isEmpty()) {
                optionalRating = Optional.of(
                        RatingEntity.builder()
                        .article(ArticleEntity.builder().id(articleId).build())
                        .user(UserEntity.builder().id(userId).build())
                        .build()
                );
            }

            RatingEntity ratingEntity = optionalRating.get();
            ratingEntity.setRatingValue(rating);
            ratingSpringRepository.save(ratingEntity);
        } else {
            throw new ArticleNotFoundException(articleId);
        }
    }

}
