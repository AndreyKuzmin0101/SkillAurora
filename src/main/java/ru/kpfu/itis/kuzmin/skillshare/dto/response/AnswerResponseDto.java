package ru.kpfu.itis.kuzmin.skillshare.dto.response;

import java.sql.Date;

public record AnswerResponseDto(String content, Date createdDate, UserResponseDto author, Boolean bestAnswer) {
}
