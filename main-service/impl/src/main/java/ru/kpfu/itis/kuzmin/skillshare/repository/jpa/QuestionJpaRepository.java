package ru.kpfu.itis.kuzmin.skillshare.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.kpfu.itis.kuzmin.skillshare.model.QuestionEntity;
import ru.kpfu.itis.kuzmin.skillshare.model.QuestionStatus;
import ru.kpfu.itis.kuzmin.skillshare.model.TagEntity;

import java.util.List;

public interface QuestionJpaRepository {
    Page<QuestionEntity> findQuestionsByFilter(Pageable pageable, Boolean noAnswers, QuestionStatus status, List<TagEntity> tags);
}
