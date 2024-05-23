package ru.kpfu.itis.skillshare.mainservice.service;

import ru.kpfu.itis.skillshare.mainservice.dto.request.ArticleFilter;
import ru.kpfu.itis.skillshare.mainservice.dto.request.ArticleRequestDto;
import ru.kpfu.itis.skillshare.mainservice.dto.response.ArticleResponseDto;

import java.util.List;

public interface ArticleService {
    ArticleResponseDto getById(Long id);
    Long save(Long authorId, ArticleRequestDto articleDto);
    List<ArticleResponseDto> getPageFiltered(ArticleFilter filter);
    List<ArticleResponseDto> getPageWaiting(Integer page, Integer size);
    void setModerationStatus(Long articleId, String moderationStatus);
}
