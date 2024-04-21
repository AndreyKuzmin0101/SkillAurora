package ru.kpfu.itis.kuzmin.skillshare.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.ArticleRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.ArticleResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.exception.alreadyexitsts.ArticleAlreadyExistsException;
import ru.kpfu.itis.kuzmin.skillshare.exception.notfound.ArticleNotFoundException;
import ru.kpfu.itis.kuzmin.skillshare.exception.notfound.TagNotFoundException;
import ru.kpfu.itis.kuzmin.skillshare.mapper.ArticleMapper;
import ru.kpfu.itis.kuzmin.skillshare.model.ArticleEntity;
import ru.kpfu.itis.kuzmin.skillshare.model.TagEntity;
import ru.kpfu.itis.kuzmin.skillshare.model.UserEntity;
import ru.kpfu.itis.kuzmin.skillshare.repository.ArticleRepository;
import ru.kpfu.itis.kuzmin.skillshare.repository.TagRepository;
import ru.kpfu.itis.kuzmin.skillshare.service.ArticleService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final TagRepository tagRepository;

    private final ArticleMapper articleMapper;


    @Override
    public ArticleResponseDto getById(Long id) {
        Optional<ArticleEntity> articleOptional = articleRepository.findById(id);
        if (articleOptional.isPresent()) {
            return articleMapper.toResponse(articleOptional.get());
        } else {
            throw new ArticleNotFoundException(id);
        }
    }

    @Override
    public Long save(ArticleRequestDto articleDto) {
        if (articleRepository.findByTitle(articleDto.title()).isPresent()) {
            throw new ArticleAlreadyExistsException(articleDto.title());
        }

        ArticleEntity article = articleMapper.toEntity(articleDto);
        article.setPublicationDate(new Date(System.currentTimeMillis()));
        article.setModerationStatus("waiting");
        article.setAuthor(new UserEntity(1L, null,null,null,null,null));

        String[] tags = articleDto.tags().split(",");
        List<TagEntity> tagEntities = new ArrayList<>();
        for (String tag: tags) {
            Optional<TagEntity> tagOptional = tagRepository.findByName(tag);
            if (tagOptional.isPresent()) {
                tagEntities.add(tagOptional.get());
            } else {
                throw new TagNotFoundException(tag);
            }
        }
        article.setTags(tagEntities);

        return articleRepository
                .save(article)
                .getId();
    }
}
