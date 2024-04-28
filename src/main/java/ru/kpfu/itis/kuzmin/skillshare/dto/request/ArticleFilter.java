package ru.kpfu.itis.kuzmin.skillshare.dto.request;

import java.util.List;

public record ArticleFilter (Integer size, Integer page, String showFirst,
                             Integer ratingThreshold, String period, List<TagRequestDto> tags) {
}
