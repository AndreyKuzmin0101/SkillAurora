package ru.kpfu.itis.kuzmin.skillshare.dto.request;

import java.sql.Date;

public record UserRequestDto(String username, String realName, Integer age, String email, String country, String city, String password, Date registerDate, Integer rating) {
}
