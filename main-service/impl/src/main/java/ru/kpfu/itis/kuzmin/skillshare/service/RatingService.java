package ru.kpfu.itis.kuzmin.skillshare.service;

public interface RatingService {

    Long getArticleRating(Long articleId);

    Integer getRatingByCurrUserForArticle(Long articleId);

    void plusToArticle(Long articleId);

    void minusToArticle(Long articleId);
}
