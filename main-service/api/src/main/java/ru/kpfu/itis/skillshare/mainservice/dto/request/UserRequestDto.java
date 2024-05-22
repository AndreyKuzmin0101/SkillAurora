package ru.kpfu.itis.skillshare.mainservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Модель для создания/обновления пользователя")
public record UserRequestDto(String username, String realName, Integer age, String email, String country, String city, String password, String profileImage,
                             List<TagRequestDto> skills) {
}
