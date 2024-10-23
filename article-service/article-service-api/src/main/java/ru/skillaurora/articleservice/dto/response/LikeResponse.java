package ru.skillaurora.articleservice.dto.response;

import java.util.UUID;

public record LikeResponse(
        UUID commentId,
        boolean isLiked
) {
}
