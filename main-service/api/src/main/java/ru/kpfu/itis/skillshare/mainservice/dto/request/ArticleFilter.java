package ru.kpfu.itis.skillshare.mainservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Модель для отправки параметров фильтрации статей")
public record ArticleFilter (Integer size, Integer page, String search, String showFirst,
                             Long ratingThreshold, String period, List<String> tags) {
}
