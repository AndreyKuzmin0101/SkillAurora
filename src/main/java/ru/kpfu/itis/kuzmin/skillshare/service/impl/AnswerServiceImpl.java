package ru.kpfu.itis.kuzmin.skillshare.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.AnswerRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.AnswerResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.exception.QuestionAlreadyClosedException;
import ru.kpfu.itis.kuzmin.skillshare.exception.notfound.AnswerNotFoundException;
import ru.kpfu.itis.kuzmin.skillshare.exception.notfound.QuestionNotFoundException;
import ru.kpfu.itis.kuzmin.skillshare.mapper.AnswerMapper;
import ru.kpfu.itis.kuzmin.skillshare.model.AnswerEntity;
import ru.kpfu.itis.kuzmin.skillshare.model.QuestionEntity;
import ru.kpfu.itis.kuzmin.skillshare.model.QuestionStatus;
import ru.kpfu.itis.kuzmin.skillshare.model.UserEntity;
import ru.kpfu.itis.kuzmin.skillshare.repository.spring.AnswerSpringRepository;
import ru.kpfu.itis.kuzmin.skillshare.repository.spring.QuestionSpringRepository;
import ru.kpfu.itis.kuzmin.skillshare.security.exception.AuthorizationException;
import ru.kpfu.itis.kuzmin.skillshare.service.AnswerService;
import ru.kpfu.itis.kuzmin.skillshare.security.util.SecurityUtil;
import ru.kpfu.itis.kuzmin.skillshare.utils.UserProfileUtil;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerSpringRepository answerRepository;
    private final QuestionSpringRepository questionRepository;

    private final AnswerMapper answerMapper;

    @Override
    public List<AnswerResponseDto> getAllByQuestionId(Long questionId) {
        Optional<QuestionEntity> optionalQuestion = questionRepository.findById(questionId);
        if (optionalQuestion.isPresent()) {
            List<AnswerEntity> answers = answerRepository.findAllByQuestion(optionalQuestion.get());
            return answers.stream()
                    .peek(answerEntity -> answerEntity.setAuthor(
                            UserProfileUtil.processUser(answerEntity.getAuthor())
                    ))
                    .map(answerMapper::toResponse).toList();
        }
        throw new AnswerNotFoundException(questionId);
    }

    @Override
    public Long save(Long questionId, AnswerRequestDto answerDto) {
        Optional<QuestionEntity> optionalQuestion = questionRepository.findById(questionId);
        if (optionalQuestion.isEmpty()) {
            throw new QuestionNotFoundException(questionId);
        }

        QuestionEntity question = optionalQuestion.get();

        if (question.getStatus().equals(QuestionStatus.CLOSED)) {
            throw new QuestionAlreadyClosedException(question.getId());
        }

        AnswerEntity answer = answerMapper.toEntity(answerDto);
        answer.setCreatedDate(new Date(System.currentTimeMillis()));

        UserEntity author = UserEntity.builder()
                .id(SecurityUtil.getIdAuthenticatedUser())
                .build();
        answer.setAuthor(author);

        answer.setBestAnswer(false);

        return answerRepository
                .save(answer)
                .getId();
    }

    @Override
    public void markAsTheBest(Long answerId) {
        Optional<AnswerEntity> optionalAnswer = answerRepository.findById(answerId);
        if (optionalAnswer.isPresent()) {
            AnswerEntity answer = optionalAnswer.get();
            QuestionEntity question = answer.getQuestion();

            Long userId = SecurityUtil.getIdAuthenticatedUser();

            if (question.getAuthor().getId().equals(userId)) {
                Optional<AnswerEntity> optionalBestAnswer = answerRepository.findByQuestionAndBestAnswerTrue(question);
                if (optionalBestAnswer.isPresent()) {
                    AnswerEntity bestAnswer = optionalBestAnswer.get();
                    bestAnswer.setBestAnswer(false);
                    answerRepository.save(bestAnswer);
                }

                answer.setBestAnswer(true);
                answerRepository.save(answer);

                question.setStatus(QuestionStatus.RESOLVED);
                questionRepository.save(question);
            } else {
                throw new AuthorizationException("You are not author of question");
            }
        } else {
            throw new AnswerNotFoundException(answerId);
        }
    }

    @Override
    public void unmarkTheBest(Long answerId) {
        Optional<AnswerEntity> optionalAnswer = answerRepository.findById(answerId);
        if (optionalAnswer.isPresent()) {
            AnswerEntity answer = optionalAnswer.get();
            QuestionEntity question = answer.getQuestion();

            Long userId = SecurityUtil.getIdAuthenticatedUser();
            if (question.getAuthor().getId().equals(userId)) {
                answer.setBestAnswer(false);
                answerRepository.save(answer);

                question.setStatus(QuestionStatus.OPEN);
                questionRepository.save(question);
            } else {
                throw new AuthorizationException("You are not author of question");
            }
        } else {
            throw new AnswerNotFoundException(answerId);
        }
    }
}
