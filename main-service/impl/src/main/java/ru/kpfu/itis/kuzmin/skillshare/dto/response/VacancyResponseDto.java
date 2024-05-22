package ru.kpfu.itis.kuzmin.skillshare.dto.response;

import java.sql.Date;

public record VacancyResponseDto(String content, Date createdDate, String moderationStatus) {
}
