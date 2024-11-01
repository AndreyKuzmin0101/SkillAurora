package ru.skillaurora.articleservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.skillaurora.articleservice.dto.response.BaseArticleResponse;
import ru.skillaurora.articleservice.dto.response.LikeResponse;
import ru.skillaurora.articleservice.repository.ArticleRatingRepository;
import ru.skillaurora.articleservice.service.RatingService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final ArticleRatingRepository articleRatingRepository;

    @Override
    public void likeArticle(UUID articleId) {

    }

    @Override
    public void dislikeArticle(UUID articleId) {

    }

    @Override
    public boolean checkLikeArticle(UUID articleId) {
        return false;
    }

    @Override
    public Page<BaseArticleResponse> getLikedArticlesByAuthUser(int page, int size) {
        return null;
    }

    @Override
    public void likeComment(UUID commentId) {

    }

    @Override
    public void dislikeComment(UUID commentId) {

    }

    @Override
    public List<LikeResponse> bulkCheckLikeComments(UUID articleId) {
        return List.of();
    }
}
