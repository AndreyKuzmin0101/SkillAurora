package ru.skillaurora.articleservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TagRequest(
        @NotBlank(message = "Название не должно быть пустым.")
        @Size(max = 16, message = "Название не должно превышать 16 символов.")
        String name,
        @Size(max = 128, message = "Описание не может превышать 128 символов.")
        String description
) {
}
