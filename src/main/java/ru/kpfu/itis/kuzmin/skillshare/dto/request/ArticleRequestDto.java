package ru.kpfu.itis.kuzmin.skillshare.dto.request;

import java.util.List;

public record ArticleRequestDto(String title, String content, String cover, String description, List<TagRequestDto> tags) {
}
