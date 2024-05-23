package ru.kpfu.itis.skillshare.mainservice.service;

import ru.kpfu.itis.skillshare.mainservice.dto.request.QuestionFilter;
import ru.kpfu.itis.skillshare.mainservice.dto.request.QuestionRequestDto;
import ru.kpfu.itis.skillshare.mainservice.dto.response.QuestionResponseDto;

import java.util.List;

public interface QuestionService {
    QuestionResponseDto getById(Long id);

    Long save(QuestionRequestDto questionDto);

    List<QuestionResponseDto> getPageFiltered(QuestionFilter filter);

    void close(Long questionId);

    Boolean isAuthor(Long questionId);
}
