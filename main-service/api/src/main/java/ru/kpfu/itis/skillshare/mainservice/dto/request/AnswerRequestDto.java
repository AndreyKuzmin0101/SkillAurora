package ru.kpfu.itis.skillshare.mainservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель для сохранения ответа на вопрос")
public record AnswerRequestDto(String content) {
}
