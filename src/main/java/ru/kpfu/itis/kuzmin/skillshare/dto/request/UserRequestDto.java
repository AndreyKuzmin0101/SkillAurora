package ru.kpfu.itis.kuzmin.skillshare.dto.request;

import java.sql.Date;

public record UserRequestDto(String username, String email, String password, Date registerDate, Integer rating) {
}
