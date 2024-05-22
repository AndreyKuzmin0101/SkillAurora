package ru.kpfu.itis.skillshare.mainservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Модель для создания вакансии")
public record VacancyRequestDto(
        @NotBlank(message = "Vacancy cannot be blank")
        @Size(max = 30000, message = "The vacancy cannot contain more than 30,000 characters")
        String content
) {
}
