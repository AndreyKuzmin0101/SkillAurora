package ru.skillaurora.articleservice.service;

import org.springframework.data.domain.Page;
import ru.skillaurora.articleservice.dto.request.ArticleRequest;
import ru.skillaurora.articleservice.dto.request.filter.ArticleFilter;
import ru.skillaurora.articleservice.dto.response.ArticleResponse;
import ru.skillaurora.articleservice.dto.response.BaseArticleResponse;

import java.util.UUID;

public interface ArticleService {

    ArticleResponse getById(UUID id);

    Page<BaseArticleResponse> searchByFilter(ArticleFilter filter);

    UUID save(ArticleRequest articleRequest);

    void update(UUID id, ArticleRequest articleRequest);

    void delete(UUID id);

    Page<BaseArticleResponse> getAllByAuthenticatedUser(int page, int size);
}
