package ru.kpfu.itis.skillshare.mainservice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.sql.Timestamp;

@Builder
@Schema(description = "Модель для получения сообщения")
public record MessageResponseDto (String content, Timestamp sendTime, UserResponseDto sender) {
}
