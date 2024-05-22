package ru.kpfu.itis.kuzmin.skillshare.dto.response;

import java.sql.Date;
import java.util.List;

public record ArticleResponseDto(Long id, String title, String content, String cover, String description, Date publicationDate, String moderationStatus,
                                 Long views, Long rating, List<TagResponseDto> tags, UserResponseDto author) {
}
