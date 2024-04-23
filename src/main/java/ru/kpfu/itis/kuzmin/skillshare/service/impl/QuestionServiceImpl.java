package ru.kpfu.itis.kuzmin.skillshare.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.QuestionRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.QuestionResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.exception.notfound.QuestionNotFoundException;
import ru.kpfu.itis.kuzmin.skillshare.mapper.QuestionMapper;
import ru.kpfu.itis.kuzmin.skillshare.model.QuestionEntity;
import ru.kpfu.itis.kuzmin.skillshare.repository.spring.QuestionSpringRepository;
import ru.kpfu.itis.kuzmin.skillshare.service.QuestionService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionSpringRepository questionRepository;
    private final QuestionMapper questionMapper;

    @Override
    public QuestionResponseDto getById(Long id) {
        Optional<QuestionEntity> questionOptional = questionRepository.findById(id);
        if (questionOptional.isPresent()) {
            return questionMapper.toResponse(questionOptional.get());
        } else {
            throw new QuestionNotFoundException(id);
        }
    }

    @Override
    public Long save(QuestionRequestDto questionDto) {
        return questionRepository
                .save(questionMapper.toEntity(questionDto))
                .getId();
    }
}
