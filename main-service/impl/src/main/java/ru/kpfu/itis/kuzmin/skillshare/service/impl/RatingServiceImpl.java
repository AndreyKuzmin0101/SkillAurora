package ru.kpfu.itis.kuzmin.skillshare.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.kuzmin.skillshare.exception.notfound.ArticleNotFoundException;
import ru.kpfu.itis.kuzmin.skillshare.model.ArticleEntity;
import ru.kpfu.itis.kuzmin.skillshare.model.RatingEntity;
import ru.kpfu.itis.kuzmin.skillshare.model.UserEntity;
import ru.kpfu.itis.kuzmin.skillshare.repository.spring.ArticleSpringRepository;
import ru.kpfu.itis.kuzmin.skillshare.repository.spring.RatingSpringRepository;
import ru.kpfu.itis.kuzmin.skillshare.service.RatingService;
import ru.kpfu.itis.kuzmin.skillshare.security.util.SecurityUtil;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final ArticleSpringRepository articleSpringRepository;
    private final RatingSpringRepository ratingSpringRepository;

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

    @Override
    public void plusToArticle(Long articleId) {
        Long userId = SecurityUtil.getIdAuthenticatedUser();
        Integer rating = determineRatingChange(userId);
        changeArticleRating(articleId, rating);
    }

    @Override
    public void minusToArticle(Long articleId) {
        Long userId = SecurityUtil.getIdAuthenticatedUser();
        Integer rating = -determineRatingChange(userId);
        changeArticleRating(articleId, rating);
    }

    // В зависимости от репутации пользователя или иных причин оценка пользователя
    // может быть более или менее ценной
    private Integer determineRatingChange(Long userId) {
        return 10;
    }

    private void changeArticleRating(Long articleId, Integer rating) {
        Optional<ArticleEntity> optionalArticle = articleSpringRepository.findById(articleId);
        if (optionalArticle.isPresent()) {
            Long userId = SecurityUtil.getIdAuthenticatedUser();
            Optional<RatingEntity> optionalRating = ratingSpringRepository.findByArticleAndUser(articleId, userId);

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

            ratingSpringRepository.save(optionalRating.get());
        } else {
            throw new ArticleNotFoundException(articleId);
        }
    }




}
