package ru.kpfu.itis.kuzmin.skillshare.service;

import ru.kpfu.itis.kuzmin.skillshare.dto.request.ArticleFilter;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.QuestionFilter;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.QuestionRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.ArticleResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.QuestionResponseDto;

import java.util.List;

public interface QuestionService {
    QuestionResponseDto getById(Long id);

    Long save(QuestionRequestDto questionDto);

    List<QuestionResponseDto> getPageFiltered(QuestionFilter filter);

    void close(Long questionId);
}
