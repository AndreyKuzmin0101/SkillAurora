package ru.kpfu.itis.kuzmin.skillshare.dto.response;

import lombok.Builder;

@Builder
public record ChatResponseDto(Long id, UserResponseDto secondUser) {
}
