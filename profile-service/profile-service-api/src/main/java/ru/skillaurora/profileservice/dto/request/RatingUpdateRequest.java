package ru.skillaurora.profileservice.dto.request;

import java.util.UUID;

public record RatingUpdateRequest (
        UUID userId,
        int difference
) {
}
