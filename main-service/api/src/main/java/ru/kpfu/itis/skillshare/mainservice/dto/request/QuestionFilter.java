package ru.kpfu.itis.skillshare.mainservice.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import ru.kpfu.itis.skillshare.mainservice.dto.QuestionStatus;

import java.util.List;

@Schema(description = "Модель для отправки параметров фильтрации вопросов")
public record QuestionFilter(

        @NotNull(message = "It is necessary to specify the quantity")
        @Max(value = 40, message = "You can't get more than 40")
        @Positive(message = "The value must be positive")
        Integer size,

        @NotNull(message = "You must specify the page")
        @PositiveOrZero(message = "It cannot be less than 0")
        Integer page,
        String search, String showFirst, Boolean noAnswers, QuestionStatus status, List<String> tags) {
}
