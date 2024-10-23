package ru.skillaurora.articleservice.dto.response;

import java.util.UUID;

public record BaseProfileInfo (
        UUID userId,
        String username,
        String profilePicture,
        AccountStatus status
) {
}
