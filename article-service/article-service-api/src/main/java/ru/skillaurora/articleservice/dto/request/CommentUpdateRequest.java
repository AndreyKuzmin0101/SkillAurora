package ru.skillaurora.articleservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CommentUpdateRequest(
        @NotBlank(message = "Комментарий не может быть пустым.")
        @Size(max = 2000, message = "Комментарий не может быть длиннее 2000 символов.")
        String content
) {
}
