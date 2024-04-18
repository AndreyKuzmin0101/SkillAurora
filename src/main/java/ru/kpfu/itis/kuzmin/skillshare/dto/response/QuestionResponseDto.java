package ru.kpfu.itis.kuzmin.skillshare.dto.response;

import java.sql.Date;

public record QuestionResponseDto(String title, String content, Date createdDate) {
}
