package ru.kpfu.itis.skillshare.mainservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

@Schema(description = "Модель для создания/обновления пользователя")
public record UserRequestDto(

        @Size(max = 64, message = "Username cannot contain more than 64 characters")
        String username,

        @Size(max = 128, message = "Real name cannot contain more than 128 characters")
        String realName,

        @Min(0)
        @Max(120)
        Integer age,

        @Pattern(regexp = "^\\S+@\\S+\\.\\S+$", message = "Email was not validated")
        @Size(max = 128, message = "Email cannot contain more than 128 characters")
        String email,

        @Size(max = 128, message = "Country name cannot contain more than 128 characters")
        String country,

        @Size(max = 128, message = "City name cannot contain more than 128 characters")
        String city,

        @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "Password does not meet the requirements")
        String password,
        String profileImage,
        List<TagRequestDto> skills
) {
}
