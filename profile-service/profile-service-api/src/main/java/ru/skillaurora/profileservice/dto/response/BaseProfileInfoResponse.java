package ru.skillaurora.profileservice.dto.response;

import java.util.UUID;

public record BaseProfileInfoResponse(
    UUID userId,
    String username,
    String profile_picture,
    Statuses status
) {
}
