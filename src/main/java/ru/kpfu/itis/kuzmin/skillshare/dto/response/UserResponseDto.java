package ru.kpfu.itis.kuzmin.skillshare.dto.response;

import java.sql.Date;

public record UserResponseDto(String username, String email, String password, Date registerDate, Integer rating) {
}
