package ru.kpfu.itis.kuzmin.skillshare.dto.response;

import java.sql.Date;

public record UserResponseDto(String username, String realName, Integer age, String email, String country, String city, String password, Date registerDate, Integer rating) {
}
