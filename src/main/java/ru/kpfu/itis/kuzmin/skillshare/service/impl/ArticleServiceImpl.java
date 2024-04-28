package ru.kpfu.itis.kuzmin.skillshare.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.ArticleFilter;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.ArticleRequestDto;
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
import ru.kpfu.itis.kuzmin.skillshare.repository.spring.TagSpringRepository;
import ru.kpfu.itis.kuzmin.skillshare.service.ArticleService;
import ru.kpfu.itis.kuzmin.skillshare.service.TagService;
import ru.kpfu.itis.kuzmin.skillshare.utils.UserProfileUtil;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final TagService tagService;

    private final ArticleSpringRepository articleSpringRepository;
    private final ArticleJpaRepository articleJpaRepository;
    private final TagSpringRepository tagRepository;

    private final ArticleMapper articleMapper;
    private final UserMapper userMapper;

    //TODO: обновление кол-во просмотров
    @Override
    public ArticleResponseDto getById(Long id) {
        Optional<ArticleEntity> articleOptional = articleSpringRepository.findById(id);
        if (articleOptional.isPresent()) {
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
        article.setAuthor(UserEntity.builder().id(authorId).build());

        article.setTags(tagService.getListUniqTagEntitiesAndSaveNonExistent(articleDto.tags()));

        return articleSpringRepository
                .save(article)
                .getId();
    }

    @Override
    public UserResponseDto getAuthor(Long articleId) {
        Optional<UserEntity> optionalUser = articleSpringRepository.findAuthorByArticleId(articleId);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            return userMapper.toResponse(UserProfileUtil.processUser(user));
        }
        return null;
    }

    @Override
    public List<ArticleResponseDto> getPageFiltered(ArticleFilter filter) {

        List<TagEntity> tags = tagService.getListUniqTagEntitiesAndSaveNonExistent(filter.tags());
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

        Page<ArticleEntity> page = articleJpaRepository.findArticlesByFilter(
                pageable, filter.ratingThreshold(), from, to, tags
        );

        if (filter.page() > page.getTotalPages()) {
            throw new ArticleNotFoundException();
        }

        return page.stream().map(articleMapper::toResponse).toList();
    }
}
