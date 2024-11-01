package ru.skillaurora.articleservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.skillaurora.articleservice.dto.request.CommentRequest;
import ru.skillaurora.articleservice.dto.request.CommentUpdateRequest;
import ru.skillaurora.articleservice.dto.response.CommentResponse;
import ru.skillaurora.articleservice.service.CommentService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    @Override
    public Page<CommentResponse> getByArticleId(UUID articleId, int page, int size) {
        return null;
    }

    @Override
    public void update(UUID id, CommentUpdateRequest commentUpdateRequest) {

    }

    @Override
    public UUID save(UUID articleId, CommentRequest commentRequest) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
