package ru.kpfu.itis.kuzmin.skillshare.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.QuestionRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.UserRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.QuestionResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.exception.notfound.ArticleNotFoundException;
import ru.kpfu.itis.kuzmin.skillshare.exception.notfound.QuestionNotFoundException;
import ru.kpfu.itis.kuzmin.skillshare.mapper.QuestionMapper;
import ru.kpfu.itis.kuzmin.skillshare.model.ArticleEntity;
import ru.kpfu.itis.kuzmin.skillshare.model.QuestionEntity;
import ru.kpfu.itis.kuzmin.skillshare.repository.QuestionRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public QuestionResponseDto getById(Long id) {
        Optional<QuestionEntity> questionOptional = questionRepository.findById(id);
        if (questionOptional.isPresent()) {
            return questionMapper.toResponse(questionOptional.get());
        } else {
            throw new QuestionNotFoundException(id);
        }
    }

    public Long save(QuestionRequestDto questionDto) {
        return questionRepository
                .save(questionMapper.toEntity(questionDto))
                .getId();
    }
}
