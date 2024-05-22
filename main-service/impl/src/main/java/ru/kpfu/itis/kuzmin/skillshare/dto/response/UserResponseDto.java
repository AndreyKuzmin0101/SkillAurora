package ru.kpfu.itis.kuzmin.skillshare.dto.response;

import lombok.Builder;

import java.sql.Date;
import java.util.List;

@Builder
public record UserResponseDto(Long id, String username, String realName, Integer age, String email, String country,
                              String city, Date registerDate, Integer rating, String profileImage,
                              List<TagResponseDto> skills) {
}
