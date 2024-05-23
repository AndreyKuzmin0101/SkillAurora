package ru.kpfu.itis.skillshare.mainservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.skillshare.mainservice.dto.Roles;
import ru.kpfu.itis.skillshare.mainservice.dto.request.ArticleFilter;
import ru.kpfu.itis.skillshare.mainservice.dto.request.ArticleRequestDto;
import ru.kpfu.itis.skillshare.mainservice.dto.request.TagRequestDto;
import ru.kpfu.itis.skillshare.mainservice.dto.response.ArticleResponseDto;
import ru.kpfu.itis.skillshare.mainservice.exception.alreadyexitsts.ArticleAlreadyExistsException;
import ru.kpfu.itis.skillshare.mainservice.exception.notfound.ArticleNotFoundException;
import ru.kpfu.itis.skillshare.mainservice.mapper.ArticleMapper;
import ru.kpfu.itis.skillshare.mainservice.mapper.UserMapper;
import ru.kpfu.itis.skillshare.mainservice.model.ArticleEntity;
import ru.kpfu.itis.skillshare.mainservice.model.TagEntity;
import ru.kpfu.itis.skillshare.mainservice.model.UserEntity;
import ru.kpfu.itis.skillshare.mainservice.repository.jpa.ArticleJpaRepository;
import ru.kpfu.itis.skillshare.mainservice.repository.spring.ArticleSpringRepository;
import ru.kpfu.itis.skillshare.mainservice.repository.spring.RatingSpringRepository;
import ru.kpfu.itis.skillshare.mainservice.service.TagService;
import ru.kpfu.itis.skillshare.mainservice.security.JwtTokenAuthentication;
import ru.kpfu.itis.skillshare.mainservice.security.exception.AuthenticationHeaderException;
import ru.kpfu.itis.skillshare.mainservice.security.util.SecurityUtil;
import ru.kpfu.itis.skillshare.mainservice.service.ArticleService;
import ru.kpfu.itis.skillshare.mainservice.utils.UserProfileUtil;

import java.sql.Date;
import java.util.*;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final TagService tagService;

    private final ArticleSpringRepository articleSpringRepository;
    private final RatingSpringRepository ratingSpringRepository;
    private final ArticleJpaRepository articleJpaRepository;

    private final ArticleMapper articleMapper;

    //TODO: как отделить логику от безопасности
    //TODO: лучше один админский эндпоинт и один для пользователей или совмещать?

    @Override
    public ArticleResponseDto getById(Long id) {
        Optional<ArticleEntity> articleOptional = articleSpringRepository.findById(id);
        if (articleOptional.isPresent()) {
            ArticleEntity article = articleOptional.get();
            article.setViews(article.getViews() + 1);
            articleSpringRepository.saveAndFlush(article);

            article.setAuthor(UserProfileUtil.processUser(article.getAuthor()));
            article.setRating(
                    ratingSpringRepository.sumRatingByArticleId(article.getId())
            );
            ArticleResponseDto articleDto = articleMapper.toResponse(articleOptional.get());

            if (!Objects.equals(article.getModerationStatus(), "confirmed")) {
                JwtTokenAuthentication authentication;
                try {
                    authentication = SecurityUtil.getJwtTokenAuthentication();
                    if (authentication.getUserId().equals(article.getAuthor().getId())) {
                        return articleDto;
                    } else {
                        Stream<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority);
                        List<String> resolved = List.of(Roles.ADMIN.getFullName(), Roles.MODER.getFullName());
                        if (roles.anyMatch(resolved::contains)) {
                            return articleDto;
                        }
                    }
                } catch (AuthenticationHeaderException e) {
                    throw new ArticleNotFoundException(id);
                }
            } else {

                return articleDto;
            }
        }
        throw new ArticleNotFoundException(id);
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

    @Override
    public List<ArticleResponseDto> getPageWaiting(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("publicationDate"));
        Page<ArticleEntity> entitiesPage = articleSpringRepository.findAllByModerationStatus("waiting", pageable);

        if (page > entitiesPage.getTotalPages()) {
            throw new ArticleNotFoundException();
        }

        return entitiesPage.stream()
                .peek(articleEntity -> {
                            articleEntity.setAuthor(UserProfileUtil.processUser(articleEntity.getAuthor()));
                        }
                ).map(articleMapper::toResponse).toList();
    }

    @Override
    public void setModerationStatus(Long articleId, String moderationStatus) {
        Optional<ArticleEntity> optionalArticle = articleSpringRepository.findById(articleId);
        if (optionalArticle.isPresent()) {
            ArticleEntity article = optionalArticle.get();
            article.setModerationStatus(moderationStatus);
            articleSpringRepository.save(article);
        } else {
            throw new ArticleNotFoundException(articleId);
        }
    }


}
