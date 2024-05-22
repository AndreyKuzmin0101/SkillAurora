package ru.kpfu.itis.skillshare.mainservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Модель для отправки токена")
public record TokenRequest (
        @NotBlank(message = "Token cannot be blank")
        String token
){
}
