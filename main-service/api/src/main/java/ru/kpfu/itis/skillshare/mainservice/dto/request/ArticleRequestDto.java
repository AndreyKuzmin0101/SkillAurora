package ru.kpfu.itis.skillshare.mainservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Модель для сохранения статьи")
public record ArticleRequestDto(String title, String content, String cover, String description, List<TagRequestDto> tags) {
}
