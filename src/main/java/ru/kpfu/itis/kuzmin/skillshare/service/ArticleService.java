package ru.kpfu.itis.kuzmin.skillshare.service;

import ru.kpfu.itis.kuzmin.skillshare.dto.request.ArticleRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.ArticleResponseDto;

public interface ArticleService {
    ArticleResponseDto getById(Long id);

    Long save(ArticleRequestDto articleDto);
}
