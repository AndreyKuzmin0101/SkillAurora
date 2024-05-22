package ru.kpfu.itis.kuzmin.skillshare.dto.response;

import jakarta.persistence.Column;

import java.sql.Date;

public record ResumeResponseDto (String content, Date createdDate, String moderationStatus)
{
}
