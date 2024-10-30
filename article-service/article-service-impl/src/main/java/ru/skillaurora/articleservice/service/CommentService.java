package ru.skillaurora.articleservice.service;

import org.springframework.data.domain.Page;
import ru.skillaurora.articleservice.dto.request.CommentRequest;
import ru.skillaurora.articleservice.dto.request.CommentUpdateRequest;
import ru.skillaurora.articleservice.dto.response.CommentResponse;

import java.util.UUID;

public interface CommentService {

    Page<CommentResponse> getByArticleId(UUID articleId, int page, int size);

    void update(UUID id, CommentUpdateRequest commentUpdateRequest);

    UUID save(UUID articleId, CommentRequest commentRequest);

    void delete(UUID id);
}
