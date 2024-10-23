package ru.skillaurora.articleservice.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record BaseArticleResponse(
        UUID id,
        String title,
        String cover,
        String description,
        BaseProfileInfo author,
        LocalDateTime publicationTime,
        ModerStatus status,
        Long views,
        Long rating,
        List<TagResponse> tags
) {
}
