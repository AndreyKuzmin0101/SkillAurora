package ru.kpfu.itis.skillshare.mainservice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "Модель для получения чата")
public record ChatResponseDto(Long id, UserResponseDto secondUser) {
}
