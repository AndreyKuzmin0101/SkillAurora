package ru.kpfu.itis.kuzmin.skillshare.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.ArticleFilter;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.ArticleRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.TagRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.ArticleResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.UserResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.exception.alreadyexitsts.ArticleAlreadyExistsException;
import ru.kpfu.itis.kuzmin.skillshare.exception.notfound.ArticleNotFoundException;
import ru.kpfu.itis.kuzmin.skillshare.mapper.ArticleMapper;
import ru.kpfu.itis.kuzmin.skillshare.mapper.UserMapper;
import ru.kpfu.itis.kuzmin.skillshare.model.ArticleEntity;
import ru.kpfu.itis.kuzmin.skillshare.model.TagEntity;
import ru.kpfu.itis.kuzmin.skillshare.model.UserEntity;
import ru.kpfu.itis.kuzmin.skillshare.repository.jpa.ArticleJpaRepository;
import ru.kpfu.itis.kuzmin.skillshare.repository.spring.ArticleSpringRepository;
import ru.kpfu.itis.kuzmin.skillshare.repository.spring.RatingSpringRepository;
import ru.kpfu.itis.kuzmin.skillshare.repository.spring.TagSpringRepository;
import ru.kpfu.itis.kuzmin.skillshare.service.ArticleService;
import ru.kpfu.itis.kuzmin.skillshare.service.TagService;
import ru.kpfu.itis.kuzmin.skillshare.utils.UserProfileUtil;

import java.sql.Date;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final TagService tagService;

    private final ArticleSpringRepository articleSpringRepository;
    private final RatingSpringRepository ratingSpringRepository;
    private final ArticleJpaRepository articleJpaRepository;

    private final ArticleMapper articleMapper;
    private final UserMapper userMapper;

    //TODO: обновление кол-во просмотров. запрет доступа другим пользователям, если модерация не пройдена
    @Override
    public ArticleResponseDto getById(Long id) {
        Optional<ArticleEntity> articleOptional = articleSpringRepository.findById(id);
        if (articleOptional.isPresent()) {
            ArticleEntity article = articleOptional.get();
            article.setAuthor(UserProfileUtil.processUser(article.getAuthor()));
            article.setRating(
                    ratingSpringRepository.sumRatingByArticleId(article.getId())
            );
            return articleMapper.toResponse(articleOptional.get());
        } else {
            throw new ArticleNotFoundException(id);
        }
    }

    @Override
    public Long save(Long authorId, ArticleRequestDto articleDto) {
        if (articleSpringRepository.findByTitle(articleDto.title()).isPresent()) {
            throw new ArticleAlreadyExistsException(articleDto.title());
        }

        ArticleEntity article = articleMapper.toEntity(articleDto);
        article.setPublicationDate(new Date(System.currentTimeMillis()));
        article.setModerationStatus("waiting");
        article.setViews(0L);
        article.setAuthor(UserEntity.builder().id(authorId).build());

        article.setTags(tagService.getListUniqTagEntitiesAndSaveNonExistent(articleDto.tags()));

        return articleSpringRepository
                .save(article)
                .getId();
    }


    @Override
    public List<ArticleResponseDto> getPageFiltered(ArticleFilter filter) {
        List<TagRequestDto> requestTags;

        if (filter.tags() == null) {
            requestTags = new ArrayList<>();
        } else {
            requestTags = filter.tags().stream().map(name -> new TagRequestDto(name, null)).toList();
        }
        List<TagEntity> tags = tagService.getListUniqTagEntitiesAndSaveNonExistent(requestTags);
        Pageable pageable = PageRequest.of(filter.page(), filter.size(), Sort.by(filter.showFirst()));

        long currentTime = System.currentTimeMillis();
        Date from = new Date(0);
        Date to = new Date(currentTime);

        if ("day".equals(filter.period())) {
            from = new Date(currentTime-86_400_000L);
        } else if ("weak".equals(filter.period())) {
            from = new Date(currentTime-604_800_000L);
        } else if ("month".equals(filter.period())) {
            from = new Date(currentTime-2_592_000_000L);
        }

        Long ratingThreshold = filter.ratingThreshold() == null ? Long.MIN_VALUE : filter.ratingThreshold();

        Page<ArticleEntity> page = articleJpaRepository.findArticlesByFilter(
                pageable, filter.search().toLowerCase(), ratingThreshold, from, to, tags
        );

        if (filter.page() > page.getTotalPages()) {
            throw new ArticleNotFoundException();
        }

        return page.stream()
                .peek(articleEntity -> {
                            articleEntity.setAuthor(UserProfileUtil.processUser(articleEntity.getAuthor()));
                            articleEntity.setRating(
                                    ratingSpringRepository.sumRatingByArticleId(articleEntity.getId())
                            );
                        }
                ).map(articleMapper::toResponse).toList();
    }
}
