package ru.kpfu.itis.kuzmin.skillshare.service;

import ru.kpfu.itis.kuzmin.skillshare.dto.request.AnswerRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.AnswerResponseDto;

import java.util.List;

public interface AnswerService {

    List<AnswerResponseDto> getAllByQuestionId(Long questionId);
    Long save(Long questionId, AnswerRequestDto answerDto);
    void markAsTheBest(Long answerId);
    void unmarkTheBest(Long answerId);
}
