package ru.skillaurora.profileservice.dto.request;

import jakarta.validation.constraints.Size;

public record TagRequest (
        @Size(max = 16, message = "Название не должно превышать 16 символов.")
        String name,
        @Size(max = 128, message = "Описание не можнт превышать 128 символов.")
        String description
) {
}
