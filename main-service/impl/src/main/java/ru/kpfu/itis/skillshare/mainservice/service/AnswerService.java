package ru.kpfu.itis.skillshare.mainservice.service;

import ru.kpfu.itis.skillshare.mainservice.dto.request.AnswerRequestDto;
import ru.kpfu.itis.skillshare.mainservice.dto.response.AnswerResponseDto;

import java.util.List;

public interface AnswerService {

    List<AnswerResponseDto> getAllByQuestionId(Long questionId);
    AnswerResponseDto save(Long questionId, AnswerRequestDto answerDto);
    void markAsTheBest(Long answerId);
    void unmarkTheBest(Long answerId);
}
