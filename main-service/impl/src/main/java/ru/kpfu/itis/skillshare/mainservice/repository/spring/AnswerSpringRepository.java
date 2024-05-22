package ru.kpfu.itis.skillshare.mainservice.repository.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.skillshare.mainservice.model.AnswerEntity;
import ru.kpfu.itis.skillshare.mainservice.model.QuestionEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerSpringRepository extends JpaRepository<AnswerEntity, Long> {

    List<AnswerEntity> findAllByQuestion(QuestionEntity question);

    Optional<AnswerEntity> findByQuestionAndBestAnswerTrue(QuestionEntity question);
}
