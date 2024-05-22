package ru.kpfu.itis.skillshare.mainservice.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель для получения тега")
public record TagResponseDto(String name, String description, Boolean custom) {
}
