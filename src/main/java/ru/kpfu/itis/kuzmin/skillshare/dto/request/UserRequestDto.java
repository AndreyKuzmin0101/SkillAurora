package ru.kpfu.itis.kuzmin.skillshare.dto.request;

import java.sql.Date;

public record UserRequestDto(String name, String email, String password, Date registerDate, Integer rating) {
}
