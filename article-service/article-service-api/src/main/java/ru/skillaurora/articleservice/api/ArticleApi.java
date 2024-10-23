package ru.skillaurora.articleservice.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.skillaurora.articleservice.dto.request.filter.ArticleFilter;
import ru.skillaurora.articleservice.dto.request.ArticleRequest;
import ru.skillaurora.articleservice.dto.response.ArticleResponse;
import ru.skillaurora.articleservice.dto.response.BaseArticleResponse;

import java.util.UUID;

@Tag(name = "Articles API | Статьи API", description = "API статей")
@RequestMapping("/api/v2/articles")
public interface ArticleApi {

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ArticleResponse getById(@PathVariable("id") UUID id);

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    Page<BaseArticleResponse> search(@Validated @RequestBody ArticleFilter filter);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UUID create(@RequestBody @Validated ArticleRequest articleRequest);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void update(@PathVariable("id") UUID id, @RequestBody @Validated ArticleRequest articleRequest);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("id") UUID id);

    @PostMapping("/{id}/like")
    @ResponseStatus(HttpStatus.OK)
    void like(@PathVariable("id") UUID articleId);

    @PostMapping("/{id}/dislike")
    @ResponseStatus(HttpStatus.OK)
    void dislike(@PathVariable("id") UUID articleId);

    @GetMapping("/{id}/like/check")
    @ResponseStatus(HttpStatus.OK)
    boolean checkLike(@PathVariable("id") UUID articleId);

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    Page<BaseArticleResponse> getMyArticles(@RequestParam(defaultValue = "0", required = false) int page,
                                            @RequestParam(defaultValue = "20", required = false) int size);

    @GetMapping("/me/liked")
    @ResponseStatus(HttpStatus.OK)
    Page<BaseArticleResponse> getLikedArticles(@RequestParam(defaultValue = "0", required = false) int page,
                                               @RequestParam(defaultValue = "20", required = false) int size);
}
