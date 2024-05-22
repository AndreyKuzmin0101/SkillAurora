package ru.kpfu.itis.skillshare.mainservice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.kpfu.itis.skillshare.mainservice.dto.QuestionStatus;

import java.sql.Date;
import java.util.List;

@Schema(description = "Модель для получения вопроса")
public record QuestionResponseDto(Long id, String title, String content, Date createdDate,
                                  QuestionStatus status, Long views, Integer countAnswers,
                                  List<TagResponseDto> tags, UserResponseDto author) {
}
