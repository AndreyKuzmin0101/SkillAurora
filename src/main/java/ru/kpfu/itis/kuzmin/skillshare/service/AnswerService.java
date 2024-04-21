package ru.kpfu.itis.kuzmin.skillshare.service;

import ru.kpfu.itis.kuzmin.skillshare.dto.request.AnswerRequestDto;

public interface AnswerService {
    Long save(AnswerRequestDto answerDto);
}
