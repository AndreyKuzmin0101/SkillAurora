package ru.kpfu.itis.skillshare.mainservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Schema(description = "Модель для отправки сообщения")
public record MessageRequestDto(
        @NotBlank(message = "Message cannot be blank")
        @Size(max = 2000)
        String content,

        @NotNull(message = "Chat ID is required")
        Long chatId) {
}
