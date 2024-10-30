package ru.skillaurora.articleservice.service;

import org.springframework.data.domain.Page;
import ru.skillaurora.articleservice.dto.response.BaseArticleResponse;
import ru.skillaurora.articleservice.dto.response.LikeResponse;

import java.util.List;
import java.util.UUID;

public interface RatingService {

    void likeArticle(UUID articleId);

    void dislikeArticle(UUID articleId);

    boolean checkLikeArticle(UUID articleId);

    Page<BaseArticleResponse> getLikedArticlesByAuthUser(int page, int size);

    void likeComment(UUID commentId);

    void dislikeComment(UUID commentId);

    List<LikeResponse> bulkCheckLikeComments(UUID articleId);
}
