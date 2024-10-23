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
import ru.skillaurora.articleservice.dto.request.CommentRequest;
import ru.skillaurora.articleservice.dto.request.CommentUpdateRequest;
import ru.skillaurora.articleservice.dto.response.CommentResponse;
import ru.skillaurora.articleservice.dto.response.LikeResponse;

import java.util.List;
import java.util.UUID;

@Tag(name = "Comments API | Комментарии API", description = "API комментариев к статьям")
@RequestMapping("/api/v2")
public interface CommentApi {

    @GetMapping("/articles/{id}/comments")
    @ResponseStatus(HttpStatus.OK)
    Page<CommentResponse> getByArticleId(@PathVariable("id") UUID articleId,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size);

    @PutMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    void update(@PathVariable("id") UUID id, @RequestBody @Validated CommentUpdateRequest commentUpdateRequest);

    @PostMapping("/articles/{id}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    UUID create(@PathVariable("id") UUID articleId, @RequestBody @Validated CommentRequest commentRequest);

    @DeleteMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    UUID delete(@PathVariable("id") UUID id);

    @PostMapping("/comments/{id}/like")
    @ResponseStatus(HttpStatus.OK)
    void like(@PathVariable("id") UUID commentId);

    @PostMapping("/comments/{id}/dislike")
    @ResponseStatus(HttpStatus.OK)
    void dislike(@PathVariable("id") UUID commentId);

    @GetMapping("/articles/{id}/comments/like/bulk-check")
    @ResponseStatus(HttpStatus.OK)
    List<LikeResponse> bulkCheckLike(@PathVariable("id") UUID articleId);
}
