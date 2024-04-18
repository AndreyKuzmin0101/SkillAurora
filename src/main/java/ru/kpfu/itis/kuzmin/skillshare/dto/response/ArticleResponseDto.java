package ru.kpfu.itis.kuzmin.skillshare.dto.response;

import java.sql.Date;

public record ArticleResponseDto(String title, String content, Date publicationDate, String moderationStatus) {
}
