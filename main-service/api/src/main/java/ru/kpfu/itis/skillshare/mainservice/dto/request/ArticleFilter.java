package ru.kpfu.itis.skillshare.mainservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.util.List;

@Schema(description = "Модель для отправки параметров фильтрации статей")
public record ArticleFilter(
        @NotNull(message = "It is necessary to specify the quantity")
        @Max(value = 20, message = "You can't get more than 20")
        @Positive(message = "The value must be positive")
        Integer size,

        @NotNull(message = "You must specify the page")
        @PositiveOrZero(message = "It cannot be less than 0")
        Integer page,

        String search, String showFirst, Long ratingThreshold, String period, List<String> tags) {
}
