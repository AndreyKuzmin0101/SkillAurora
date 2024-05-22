package ru.kpfu.itis.skillshare.mainservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "Модель для создания чата")
public record ChatRequestDto(
        @NotNull(message = "Another user's ID is required")
        Long secondUserId
) {
}
