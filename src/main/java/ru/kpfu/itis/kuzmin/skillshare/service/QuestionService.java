package ru.kpfu.itis.kuzmin.skillshare.service;

import ru.kpfu.itis.kuzmin.skillshare.dto.request.QuestionRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.QuestionResponseDto;

public interface QuestionService {
    QuestionResponseDto getById(Long id);

    Long save(QuestionRequestDto questionDto);
}
