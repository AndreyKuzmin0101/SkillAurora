package ru.kpfu.itis.skillshare.mainservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Schema(description = "Модель для сохранения статьи")
public record ArticleRequestDto(
        @NotBlank(message = "Title cannot be blank")
        @Size(max = 128, message = "Title cannot contain more than 128 characters")
        String title,

        @NotBlank(message = "Title cannot be blank")
        String content,
        String cover,
        @NotBlank(message = "Description is necessary")
        @Size(max = 512, message = "Description cannot contain more than 512 characters")
        String description,
        List<TagRequestDto> tags) {
}
