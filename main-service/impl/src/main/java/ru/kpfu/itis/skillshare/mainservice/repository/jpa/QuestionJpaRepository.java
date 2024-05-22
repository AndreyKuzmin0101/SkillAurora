package ru.kpfu.itis.skillshare.mainservice.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.kpfu.itis.skillshare.mainservice.model.QuestionEntity;
import ru.kpfu.itis.skillshare.mainservice.dto.QuestionStatus;
import ru.kpfu.itis.skillshare.mainservice.model.TagEntity;

import java.util.List;

public interface QuestionJpaRepository {
    Page<QuestionEntity> findQuestionsByFilter(Pageable pageable, Boolean noAnswers, QuestionStatus status, List<TagEntity> tags);
}
