package ru.skillaurora.profileservice.dto.request;

import jakarta.validation.constraints.Size;

public record AboutMeRequest (
        @Size(max = 2000, message = "Максимум 2000 символов.")
        String aboutMe
) {
}
