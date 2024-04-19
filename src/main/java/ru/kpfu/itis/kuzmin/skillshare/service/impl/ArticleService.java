package ru.kpfu.itis.kuzmin.skillshare.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.ArticleRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.UserRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.ArticleResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.exception.alreadyexitsts.ArticleAlreadyExistsException;
import ru.kpfu.itis.kuzmin.skillshare.exception.notfound.ArticleNotFoundException;
import ru.kpfu.itis.kuzmin.skillshare.mapper.ArticleMapper;
import ru.kpfu.itis.kuzmin.skillshare.model.ArticleEntity;
import ru.kpfu.itis.kuzmin.skillshare.repository.ArticleRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    public ArticleResponseDto getById(Long id) {
        Optional<ArticleEntity> articleOptional = articleRepository.findById(id);
        if (articleOptional.isPresent()) {
            return articleMapper.toResponse(articleOptional.get());
        } else {
            throw new ArticleNotFoundException(id);
        }
    }

    public Long save(ArticleRequestDto articleDto) {
        if (articleRepository.findByTitle(articleDto.title()).isPresent()) {
            throw new ArticleAlreadyExistsException(articleDto.title());
        }

        return articleRepository
                .save(articleMapper.toEntity(articleDto))
                .getId();
    }
}
