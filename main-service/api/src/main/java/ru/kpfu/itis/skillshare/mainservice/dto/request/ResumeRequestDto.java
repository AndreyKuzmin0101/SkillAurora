package ru.kpfu.itis.skillshare.mainservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Модель для создания резюме")
public record ResumeRequestDto(
        @NotBlank(message = "Resume cannot be blank")
        @Size(max = 30000, message = "The resume cannot contain more than 30,000 characters")
        String content
)
{
}
