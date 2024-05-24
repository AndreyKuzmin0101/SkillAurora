package ru.kpfu.itis.skillshare.mainservice.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.skillshare.mainservice.dto.request.AnswerRequestDto;
import ru.kpfu.itis.skillshare.mainservice.dto.response.AnswerResponseDto;
import ru.kpfu.itis.skillshare.mainservice.exception.QuestionAlreadyClosedException;
import ru.kpfu.itis.skillshare.mainservice.exception.notfound.AnswerNotFoundException;
import ru.kpfu.itis.skillshare.mainservice.exception.notfound.QuestionNotFoundException;
import ru.kpfu.itis.skillshare.mainservice.mapper.AnswerMapper;
import ru.kpfu.itis.skillshare.mainservice.model.AnswerEntity;
import ru.kpfu.itis.skillshare.mainservice.model.QuestionEntity;
import ru.kpfu.itis.skillshare.mainservice.dto.QuestionStatus;
import ru.kpfu.itis.skillshare.mainservice.model.UserEntity;
import ru.kpfu.itis.skillshare.mainservice.repository.spring.AnswerSpringRepository;
import ru.kpfu.itis.skillshare.mainservice.repository.spring.QuestionSpringRepository;
import ru.kpfu.itis.skillshare.mainservice.repository.spring.UserSpringRepository;
import ru.kpfu.itis.skillshare.mainservice.security.exception.AuthorizationException;
import ru.kpfu.itis.skillshare.mainservice.security.util.XssFilterUtil;
import ru.kpfu.itis.skillshare.mainservice.service.AnswerService;
import ru.kpfu.itis.skillshare.mainservice.security.util.SecurityUtil;
import ru.kpfu.itis.skillshare.mainservice.service.RatingService;
import ru.kpfu.itis.skillshare.mainservice.utils.UserProfileUtil;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final RatingService ratingService;

    private final AnswerSpringRepository answerRepository;
    private final QuestionSpringRepository questionRepository;
    private final UserSpringRepository userSpringRepository;

    private final AnswerMapper answerMapper;

    @Override
    public List<AnswerResponseDto> getAllByQuestionId(Long questionId) {
        Optional<QuestionEntity> optionalQuestion = questionRepository.findById(questionId);
        if (optionalQuestion.isPresent()) {
            List<AnswerEntity> answers = answerRepository.findAllByQuestion(optionalQuestion.get());
            List<AnswerEntity> sortedAnswers = new ArrayList<>();
            for (AnswerEntity answer: answers) {
                if (answer.getBestAnswer()) {
                    sortedAnswers.add(answer);
                    break;
                }
            }
            if (!sortedAnswers.isEmpty()) {
                for (AnswerEntity answer: answers) {
                    if (answer.getBestAnswer()) continue;
                    sortedAnswers.add(answer);
                }
            } else {
                sortedAnswers = answers;
            }

            return sortedAnswers.stream()
                    .peek(answerEntity -> answerEntity.setAuthor(
                            UserProfileUtil.processUser(answerEntity.getAuthor())
                    ))
                    .map(answerMapper::toResponse).toList();
        }
        throw new AnswerNotFoundException(questionId);
    }

    @Transactional
    @Override
    public AnswerResponseDto save(Long questionId, AnswerRequestDto answerDto) {
        Optional<QuestionEntity> optionalQuestion = questionRepository.findById(questionId);
        if (optionalQuestion.isEmpty()) {
            throw new QuestionNotFoundException(questionId);
        }

        QuestionEntity question = optionalQuestion.get();

        if (question.getStatus().equals(QuestionStatus.CLOSED)) {
            throw new QuestionAlreadyClosedException(question.getId());
        }

        AnswerEntity answer = answerMapper.toEntity(answerDto);

        answer.setContent(XssFilterUtil.protect(answer.getContent()));

        answer.setCreatedDate(new Date(System.currentTimeMillis()));

        answer.setQuestion(question);

        UserEntity author = userSpringRepository.findById(SecurityUtil.getIdAuthenticatedUser()).get();
        answer.setAuthor(author);

        answer.setBestAnswer(false);

        AnswerEntity answerEntity = answerRepository.saveAndFlush(answer);
        answerEntity.setAuthor(UserProfileUtil.processUser(answerEntity.getAuthor()));
        return answerMapper.toResponse(answerEntity);
    }

    @Transactional
    @Override
    public void markAsTheBest(Long answerId) {
        Optional<AnswerEntity> optionalAnswer = answerRepository.findById(answerId);
        if (optionalAnswer.isPresent()) {
            AnswerEntity answer = optionalAnswer.get();
            QuestionEntity question = answer.getQuestion();

            Long userId = SecurityUtil.getIdAuthenticatedUser();

            if (question.getAuthor().getId().equals(userId)) {
                if (question.getStatus().equals(QuestionStatus.CLOSED)) {
                    throw new QuestionAlreadyClosedException(answerId);
                }

                Optional<AnswerEntity> optionalBestAnswer = answerRepository.findByQuestionAndBestAnswerTrue(question);
                if (optionalBestAnswer.isPresent()) {
                    AnswerEntity bestAnswer = optionalBestAnswer.get();
                    bestAnswer.setBestAnswer(false);
                    answerRepository.save(bestAnswer);

                    UserEntity author = bestAnswer.getAuthor();
                    author.setRating(author.getRating() - 20);
                    ratingService.checkCurrentRating(author.getId());
                }

                answer.setBestAnswer(true);
                answerRepository.save(answer);

                question.setStatus(QuestionStatus.RESOLVED);
                questionRepository.save(question);

                UserEntity author = answer.getAuthor();
                author.setRating(author.getRating() + 20);
                userSpringRepository.save(author);
                ratingService.checkCurrentRating(author.getId());
            } else {
                throw new AuthorizationException("You are not author of question");
            }
        } else {
            throw new AnswerNotFoundException(answerId);
        }
    }

    @Transactional
    @Override
    public void unmarkTheBest(Long answerId) {
        Optional<AnswerEntity> optionalAnswer = answerRepository.findById(answerId);
        if (optionalAnswer.isPresent()) {
            AnswerEntity answer = optionalAnswer.get();
            QuestionEntity question = answer.getQuestion();

            Long userId = SecurityUtil.getIdAuthenticatedUser();
            if (answer.getBestAnswer() && question.getAuthor().getId().equals(userId)) {
                answer.setBestAnswer(false);
                answerRepository.save(answer);

                question.setStatus(QuestionStatus.OPEN);
                questionRepository.save(question);

                UserEntity author = answer.getAuthor();
                author.setRating(author.getRating() - 20);
                userSpringRepository.save(author);
            } else {
                throw new AuthorizationException("You are not author of question");
            }
        } else {
            throw new AnswerNotFoundException(answerId);
        }
    }

}
