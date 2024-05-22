package ru.kpfu.itis.skillshare.mainservice.service;

public interface RatingService {

    Long getArticleRating(Long articleId);

    Integer getRatingByCurrUserForArticle(Long articleId);

    void plusToArticle(Long articleId);

    void minusToArticle(Long articleId);
}
