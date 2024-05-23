package ru.kpfu.itis.skillshare.mainservice.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.skillshare.mainservice.dto.request.QuestionFilter;
import ru.kpfu.itis.skillshare.mainservice.dto.request.QuestionRequestDto;
import ru.kpfu.itis.skillshare.mainservice.dto.request.TagRequestDto;
import ru.kpfu.itis.skillshare.mainservice.dto.response.QuestionResponseDto;
import ru.kpfu.itis.skillshare.mainservice.exception.notfound.AnswerNotFoundException;
import ru.kpfu.itis.skillshare.mainservice.exception.notfound.QuestionNotFoundException;
import ru.kpfu.itis.skillshare.mainservice.mapper.QuestionMapper;
import ru.kpfu.itis.skillshare.mainservice.model.QuestionEntity;
import ru.kpfu.itis.skillshare.mainservice.dto.QuestionStatus;
import ru.kpfu.itis.skillshare.mainservice.model.TagEntity;
import ru.kpfu.itis.skillshare.mainservice.model.UserEntity;
import ru.kpfu.itis.skillshare.mainservice.repository.jpa.QuestionJpaRepository;
import ru.kpfu.itis.skillshare.mainservice.repository.spring.AnswerSpringRepository;
import ru.kpfu.itis.skillshare.mainservice.repository.spring.QuestionSpringRepository;
import ru.kpfu.itis.skillshare.mainservice.security.exception.AuthenticationHeaderException;
import ru.kpfu.itis.skillshare.mainservice.security.exception.AuthorizationException;
import ru.kpfu.itis.skillshare.mainservice.security.util.SecurityUtil;
import ru.kpfu.itis.skillshare.mainservice.service.QuestionService;
import ru.kpfu.itis.skillshare.mainservice.service.TagService;
import ru.kpfu.itis.skillshare.mainservice.utils.UserProfileUtil;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final TagService tagService;

    private final QuestionSpringRepository questionSpringRepository;
    private final QuestionJpaRepository questionJpaRepository;
    private final AnswerSpringRepository answerSpringRepository;

    private final QuestionMapper questionMapper;

    @Override
    public QuestionResponseDto getById(Long id) {
        Optional<QuestionEntity> questionOptional = questionSpringRepository.findById(id);
        if (questionOptional.isPresent()) {
            QuestionEntity question = questionOptional.get();
            question.setViews(question.getViews() + 1);

            questionSpringRepository.increaseViews(question.getId());

            question.setAuthor(UserProfileUtil.processUser(question.getAuthor()));
            question.setCountAnswers(answerSpringRepository.findAllByQuestion(question).size());
            return questionMapper.toResponse(question);
        } else {
            throw new AnswerNotFoundException(id);
        }
    }

    @Override
    public Long save(QuestionRequestDto questionDto) {
        QuestionEntity question = questionMapper.toEntity(questionDto);
        question.setCreatedDate(new Date(System.currentTimeMillis()));
        question.setStatus(QuestionStatus.OPEN);
        question.setViews(0L);

        UserEntity author = UserEntity.builder()
                .id(SecurityUtil.getIdAuthenticatedUser())
                .build();
        question.setAuthor(author);

        question.setTags(tagService.getListUniqTagEntitiesAndSaveNonExistent(questionDto.tags()));

        return questionSpringRepository
                .save(question)
                .getId();
    }

    @Override
    public List<QuestionResponseDto> getPageFiltered(QuestionFilter filter) {
        List<TagRequestDto> requestTags;
        if (filter.tags() == null) {
            requestTags = new ArrayList<>();
        } else {
            requestTags = filter.tags().stream().map(name -> new TagRequestDto(name, null)).toList();
        }
        List<TagEntity> tags = tagService.getListUniqTagEntitiesAndSaveNonExistent(requestTags);

        Pageable pageable = PageRequest.of(filter.page(), filter.size(), Sort.by(filter.showFirst()));

        Page<QuestionEntity> page = questionJpaRepository.findQuestionsByFilter(
                pageable, filter.noAnswers(), filter.status(), tags
        );

        if (filter.page() > page.getTotalPages()) {
            throw new QuestionNotFoundException();
        }

        return page.stream()
                .peek(questionEntity -> {
                            questionEntity.setAuthor(UserProfileUtil.processUser(questionEntity.getAuthor()));
                            questionEntity.setCountAnswers(
                                    answerSpringRepository.findAllByQuestion(questionEntity).size()
                            );
                        }
                ).map(questionMapper::toResponse)
                .toList();
    }

    @Override
    public void close(Long questionId) {
        Optional<QuestionEntity> optionalQuestion = questionSpringRepository.findById(questionId);
        if (optionalQuestion.isPresent()) {
            QuestionEntity question = optionalQuestion.get();
            if (question.getAuthor().getId().equals(SecurityUtil.getIdAuthenticatedUser())) {
                question.setStatus(QuestionStatus.CLOSED);
                questionSpringRepository.save(question);
            } else {
                throw new AuthorizationException("An access error has occurred");
            }
        } else {
            throw new QuestionNotFoundException(questionId);
        }
    }

    @Override
    public Boolean isAuthor(Long questionId) {
        try {
            Long userId = SecurityUtil.getIdAuthenticatedUser();
            Optional<QuestionEntity> optionalQuestion = questionSpringRepository.findById(questionId);
            if (optionalQuestion.isPresent()) {
                QuestionEntity question = optionalQuestion.get();
                return question.getAuthor().getId().equals(userId);
            } else {
                throw new QuestionNotFoundException(questionId);
            }

        } catch (AuthenticationHeaderException e) {
            return false;
        }
    }
}
