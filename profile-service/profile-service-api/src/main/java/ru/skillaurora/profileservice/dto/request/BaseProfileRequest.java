package ru.skillaurora.profileservice.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record BaseProfileRequest (
        UUID userId,
        @Size(min = 3, max = 32, message = "Username не должен короче 3 символов и превышать 32.")
        @Pattern(regexp = "^[a-zA-Z0-9._-]+$", message = "Разрешено использовать латиницу, цифры и спец. символы: точка, подчёркивание и дефис")
        String username
) {
}
