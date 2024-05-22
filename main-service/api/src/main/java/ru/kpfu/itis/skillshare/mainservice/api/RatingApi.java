package ru.kpfu.itis.skillshare.mainservice.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Ratings | Рейтинги", description = "API рейтинга")
@RequestMapping("/api/v1/ratings")
public interface RatingApi {

    @Operation(
            summary = "Получить мою оценку статьи",
            description = "Позволяет получить данные о поставленной статье оценке"
    )
    @SecurityRequirement(name = "JWT")
    @GetMapping("/articles/{article-id}/me")
    @ResponseStatus(HttpStatus.OK)
    Integer getMyRatingForArticle(@PathVariable("article-id") Long id);

    @Operation(
            summary = "Прибавить рейтинг статьи",
            description = "Позволяет увеличить рейтинг статьи по её id"
    )
    @SecurityRequirement(name = "JWT")
    @PostMapping("/articles/{article-id}/plus")
    @ResponseStatus(HttpStatus.OK)
    Long plusRating(@PathVariable("article-id") Long id);

    @Operation(
            summary = "Убавить рейтинг статьи",
            description = "Позволяет уменьшить рейтинг статьи по её id"
    )
    @SecurityRequirement(name = "JWT")
    @PostMapping("/articles/{article-id}/minus")
    @ResponseStatus(HttpStatus.OK)
    Long minusRating(@PathVariable("article-id") Long id);

}
