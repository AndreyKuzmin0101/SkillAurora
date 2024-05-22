package ru.kpfu.itis.skillshare.mainservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Schema(description = "Модель для создания вопроса")
public record QuestionRequestDto(

        @NotBlank(message = "Title cannot be blank")
        @Size(max = 128, message = "Title cannot contain more than 128 characters")
        String title,

        @NotBlank(message = "Question cannot be blank")
        @Size(max = 20000, message = "The question cannot contain more than 20,000 characters")
        String content,
        List<TagRequestDto> tags
) {
}
