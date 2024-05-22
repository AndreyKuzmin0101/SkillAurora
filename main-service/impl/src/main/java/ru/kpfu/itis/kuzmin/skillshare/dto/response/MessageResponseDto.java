package ru.kpfu.itis.kuzmin.skillshare.dto.response;

import lombok.Builder;

import java.sql.Timestamp;

@Builder
public record MessageResponseDto (String content, Timestamp sendTime, UserResponseDto sender) {
}
