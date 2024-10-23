package ru.skillaurora.articleservice.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CommentResponse (
    UUID id,
    String content,
    List<CommentResponse> replies,
    LocalDateTime createdAt,
    Integer rating
) {
}
