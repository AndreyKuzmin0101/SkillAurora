package ru.skillaurora.articleservice.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ArticleResponse(
        UUID id,
        String title,
        String content,
        BaseProfileInfo author,
        LocalDateTime publicationTime,
        Long views,
        Long rating,
        List<TagResponse> tags
) {
}
