package ru.kpfu.itis.kuzmin.skillshare.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.AnswerRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.mapper.AnswerMapper;
import ru.kpfu.itis.kuzmin.skillshare.repository.spring.AnswerSpringRepository;
import ru.kpfu.itis.kuzmin.skillshare.service.AnswerService;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerSpringRepository answerRepository;
    private final AnswerMapper answerMapper;

    @Override
    public Long save(AnswerRequestDto answerDto) {
        return answerRepository
                .save(answerMapper.toEntity(answerDto))
                .getId();
    }
}
