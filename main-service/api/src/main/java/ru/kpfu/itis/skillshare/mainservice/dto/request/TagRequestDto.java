package ru.kpfu.itis.skillshare.mainservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Schema(description = "Модель для отправки тегов")
public record TagRequestDto(
        @NotBlank(message = "Name cannot be null")
        @Size(max = 32, message = "Name cannot contain more than 32 characters")
        String name,

        @Size(max = 64, message = "Description cannot contain more than 64 characters")
        String description) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagRequestDto that = (TagRequestDto) o;
        return Objects.equals(name, that.name);
    }
}
