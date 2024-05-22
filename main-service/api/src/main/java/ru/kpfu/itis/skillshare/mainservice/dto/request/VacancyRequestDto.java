package ru.kpfu.itis.skillshare.mainservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель для создания вакансии")
public record VacancyRequestDto(String content) {
}
