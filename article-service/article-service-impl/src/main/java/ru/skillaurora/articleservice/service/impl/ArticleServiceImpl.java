package ru.skillaurora.articleservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.skillaurora.articleservice.controller.client.ProfileServiceClient;
import ru.skillaurora.articleservice.dto.request.ArticleRequest;
import ru.skillaurora.articleservice.dto.request.filter.ArticleFilter;
import ru.skillaurora.articleservice.dto.response.ArticleResponse;
import ru.skillaurora.articleservice.dto.response.BaseArticleResponse;
import ru.skillaurora.articleservice.dto.response.BaseProfileInfo;
import ru.skillaurora.articleservice.exception.model.ArticleNotFoundException;
import ru.skillaurora.articleservice.mapper.ArticleMapper;
import ru.skillaurora.articleservice.model.ArticleEntity;
import ru.skillaurora.articleservice.repository.ArticleRepository;
import ru.skillaurora.articleservice.service.ArticleService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ProfileServiceClient profileServiceClient;

    private final ArticleRepository articleRepository;

    private final ArticleMapper articleMapper;

    @Override
    public ArticleResponse getById(UUID id) {
        ArticleEntity articleEntity = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException(id));

        BaseProfileInfo baseProfileInfo = profileServiceClient.getOneByUserId(articleEntity.getId());
        articleEntity.setAuthor(baseProfileInfo);

        return articleMapper.toResponse(articleEntity);
    }

    @Override
    public Page<BaseArticleResponse> searchByFilter(ArticleFilter filter, boolean similar) {
        return null;
    }

    @Override
    public UUID save(ArticleRequest articleRequest) {
        return null;
    }

    @Override
    public void update(UUID id, ArticleRequest articleRequest) {

    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public Page<BaseArticleResponse> getAllByAuthenticatedUser(int page, int size) {
        return null;
    }
}
