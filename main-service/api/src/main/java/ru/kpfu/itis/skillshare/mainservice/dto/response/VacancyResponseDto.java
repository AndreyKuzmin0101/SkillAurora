package ru.kpfu.itis.skillshare.mainservice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.sql.Date;

@Schema(description = "Модель для получения вакансии")
public record VacancyResponseDto(String content, Date createdDate, String moderationStatus) {
}
