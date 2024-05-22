package ru.kpfu.itis.skillshare.mainservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Модель для сохранения ответа на вопрос")
public record AnswerRequestDto(
        @NotBlank(message = "Answer cannot be blank")
        @Size(max = 20000, message = "The response cannot contain more than 20,000 characters")
        String content
) {
}
