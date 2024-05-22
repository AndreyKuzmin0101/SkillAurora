package ru.kpfu.itis.skillshare.mainservice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.sql.Date;
import java.util.List;

@Builder
@Schema(description = "Модель для получения пользователя")
public record UserResponseDto(Long id, String username, String realName, Integer age, String email, String country,
                              String city, Date registerDate, Integer rating, String profileImage,
                              List<TagResponseDto> skills) {
}
