package ru.skillaurora.articleservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;
import ru.skillaurora.articleservice.api.ArticleApi;
import ru.skillaurora.articleservice.dto.request.ArticleRequest;
import ru.skillaurora.articleservice.dto.request.filter.ArticleFilter;
import ru.skillaurora.articleservice.dto.response.ArticleResponse;
import ru.skillaurora.articleservice.dto.response.BaseArticleResponse;
import ru.skillaurora.articleservice.service.ArticleService;
import ru.skillaurora.articleservice.service.RatingService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ArticleController implements ArticleApi {

    private final ArticleService articleService;
    private final RatingService ratingService;

    @Override
    public ArticleResponse getById(UUID id) {
        return articleService.getById(id);
    }

    @Override
    public Page<BaseArticleResponse> search(ArticleFilter filter) {
        return articleService.searchByFilter(filter);
    }

    @Override
    public UUID create(ArticleRequest articleRequest) {
        return articleService.save(articleRequest);
    }

    @Override
    public void update(UUID id, ArticleRequest articleRequest) {
        articleService.update(id, articleRequest);
    }

    @Override
    public void delete(UUID id) {
        articleService.delete(id);
    }

    @Override
    public void like(UUID articleId) {
        ratingService.likeArticle(articleId);
    }

    @Override
    public void dislike(UUID articleId) {
        ratingService.dislikeArticle(articleId);
    }

    @Override
    public boolean checkLike(UUID articleId) {
        return ratingService.checkLikeArticle(articleId);
    }

    @Override
    public Page<BaseArticleResponse> getMyArticles(int page, int size) {
        return articleService.getAllByAuthenticatedUser(page, size);
    }

    @Override
    public Page<BaseArticleResponse> getLikedArticles(int page, int size) {
        return ratingService.getLikedArticlesByAuthUser(page, size);
    }
}
