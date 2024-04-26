package ru.kpfu.itis.kuzmin.skillshare.dto.response;

import java.sql.Date;
import java.util.List;

public record UserResponseDto(Long id, String username, String realName, Integer age, String email, String country, String city, Date registerDate, Integer rating, String profileImage,
                              List<TagResponseDto> skills) {
}
