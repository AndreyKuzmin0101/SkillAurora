package ru.kpfu.itis.skillshare.mainservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель для отправки сообщения")
public record MessageRequestDto(String content, Long chatId) {
}
