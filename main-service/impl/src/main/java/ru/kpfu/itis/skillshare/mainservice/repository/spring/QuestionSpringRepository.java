package ru.kpfu.itis.skillshare.mainservice.repository.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.skillshare.mainservice.model.QuestionEntity;

@Repository
public interface QuestionSpringRepository extends JpaRepository<QuestionEntity, Long> {
}
