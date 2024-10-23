package ru.skillaurora.articleservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ArticleRequest(

        @NotBlank(message = "Название не может отсутствовать.")
        @Size(max = 128, message = "Название не может содержать более 128 символов.")
        String title,

        @NotBlank(message = "Содержимое статьи не может быть пустым.")
        String content,

        String cover,

        @NotBlank(message = "Необходимо описание.")
        @Size(max = 512, message = "Описание не может быть длиннее 512 символов.")
        String description
) {
}
