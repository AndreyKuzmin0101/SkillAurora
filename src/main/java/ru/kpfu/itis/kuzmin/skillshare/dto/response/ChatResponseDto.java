package ru.kpfu.itis.kuzmin.skillshare.dto.response;

public record ChatResponseDto(Long id, UserResponseDto currentUser, UserResponseDto activeContact) {
}
