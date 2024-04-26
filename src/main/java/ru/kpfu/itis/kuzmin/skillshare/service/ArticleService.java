package ru.kpfu.itis.kuzmin.skillshare.service;

import ru.kpfu.itis.kuzmin.skillshare.dto.request.ArticleRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.ArticleResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.UserResponseDto;

public interface ArticleService {
    ArticleResponseDto getById(Long id);
    Long save(Long authorId, ArticleRequestDto articleDto);

    UserResponseDto getAuthor(Long articleId);
}
