package ru.kpfu.itis.kuzmin.skillshare.dto.response;

import java.sql.Date;
import java.util.List;

public record ArticleResponseDto(String title, String content, Date publicationDate, String moderationStatus,
                                 List<TagResponseDto> tags) {
}
