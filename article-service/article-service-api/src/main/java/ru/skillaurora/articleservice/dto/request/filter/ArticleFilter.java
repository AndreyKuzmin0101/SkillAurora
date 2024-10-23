package ru.skillaurora.articleservice.dto.request.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

@Schema(description = "Модель для отправки параметров фильтрации статей")
public record ArticleFilter(

        @NotNull(message = "Необходимо указать количество.")
        @Max(value = 30, message = "Нельзя за раз получить больше 30-ти статей.")
        @Positive(message = "Количество должно быть положительным.")
        @JsonProperty(defaultValue = "20")
        Integer size,

        @NotNull(message = "Необходимо указать номер страницы.")
        @PositiveOrZero(message = "Номер страницы не может быть меньше нуля.")
        @JsonProperty(defaultValue = "0")
        Integer page,

        String search,
        ShowFirst showFirst,
        Long ratingThreshold,
        Period period,
        List<Long> tags
) {
}
