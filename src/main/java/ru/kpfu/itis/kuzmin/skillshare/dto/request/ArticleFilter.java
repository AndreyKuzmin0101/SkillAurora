package ru.kpfu.itis.kuzmin.skillshare.dto.request;

import java.util.List;

public record ArticleFilter (Integer size, Integer page, String search, String showFirst,
                             Long ratingThreshold, String period, List<String> tags) {
}
