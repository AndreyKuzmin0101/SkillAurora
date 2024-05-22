package ru.kpfu.itis.kuzmin.skillshare.dto.request;

import ru.kpfu.itis.kuzmin.skillshare.model.QuestionStatus;

import java.util.List;

public record QuestionFilter(Integer size, Integer page, String search, String showFirst,
                             Boolean noAnswers, QuestionStatus status, List<String> tags) {
}
