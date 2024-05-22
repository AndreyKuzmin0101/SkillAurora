package ru.kpfu.itis.skillshare.mainservice.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import ru.kpfu.itis.skillshare.mainservice.dto.QuestionStatus;

import java.util.List;

@Schema(description = "Модель для отправки параметров фильтрации вопросов")
public record QuestionFilter(Integer size, Integer page, String search, String showFirst,
                             Boolean noAnswers, QuestionStatus status, List<String> tags) {
}
