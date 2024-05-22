package ru.kpfu.itis.kuzmin.skillshare.dto.response;

import ru.kpfu.itis.kuzmin.skillshare.model.QuestionStatus;

import java.sql.Date;
import java.util.List;

public record QuestionResponseDto(Long id, String title, String content, Date createdDate,
                                  QuestionStatus status, Long views, Integer countAnswers,
                                  List<TagResponseDto> tags, UserResponseDto author) {
}
