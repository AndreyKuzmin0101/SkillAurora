package ru.kpfu.itis.skillshare.mainservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Модель для создания вопроса")
public record QuestionRequestDto(String title, String content, List<TagRequestDto> tags) {
}
