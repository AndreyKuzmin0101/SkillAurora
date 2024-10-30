package ru.skillaurora.articleservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;
import ru.skillaurora.articleservice.api.CommentApi;
import ru.skillaurora.articleservice.dto.request.CommentRequest;
import ru.skillaurora.articleservice.dto.request.CommentUpdateRequest;
import ru.skillaurora.articleservice.dto.response.CommentResponse;
import ru.skillaurora.articleservice.dto.response.LikeResponse;
import ru.skillaurora.articleservice.service.CommentService;
import ru.skillaurora.articleservice.service.RatingService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CommentController implements CommentApi {

    private final CommentService commentService;
    private final RatingService ratingService;

    @Override
    public Page<CommentResponse> getByArticleId(UUID articleId, int page, int size) {
        return commentService.getByArticleId(articleId, page, size);
    }

    @Override
    public void update(UUID id, CommentUpdateRequest commentUpdateRequest) {
        commentService.update(id, commentUpdateRequest);
    }

    @Override
    public UUID create(UUID articleId, CommentRequest commentRequest) {
        return commentService.save(articleId, commentRequest);
    }

    @Override
    public void delete(UUID id) {
        commentService.delete(id);
    }

    @Override
    public void like(UUID commentId) {
        ratingService.likeComment(commentId);
    }

    @Override
    public void dislike(UUID commentId) {
        ratingService.dislikeComment(commentId);
    }

    @Override
    public List<LikeResponse> bulkCheckLike(UUID articleId) {
        return ratingService.bulkCheckLikeComments(articleId);
    }
}
