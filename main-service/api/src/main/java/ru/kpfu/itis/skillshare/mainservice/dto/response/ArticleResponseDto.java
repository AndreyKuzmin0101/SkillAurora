package ru.kpfu.itis.skillshare.mainservice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.sql.Date;
import java.util.List;

@Schema(description = "Модель для получения статьи")
public record ArticleResponseDto(Long id, String title, String content, String cover, String description, Date publicationDate, String moderationStatus,
                                 Long views, Long rating, List<TagResponseDto> tags, UserResponseDto author) {
}
