package ru.kpfu.itis.kuzmin.skillshare.dto.request;

import java.util.List;

public record ArticleRequestDto(String title, String content, List<TagRequestDto> tags) {
}
