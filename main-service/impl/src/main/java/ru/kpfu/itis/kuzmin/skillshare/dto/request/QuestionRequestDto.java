package ru.kpfu.itis.kuzmin.skillshare.dto.request;

import java.util.List;

public record QuestionRequestDto(String title, String content, List<TagRequestDto> tags) {
}
