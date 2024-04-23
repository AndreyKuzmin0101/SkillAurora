package ru.kpfu.itis.kuzmin.skillshare.repository.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.kuzmin.skillshare.model.AnswerEntity;

@Repository
public interface AnswerSpringRepository extends JpaRepository<AnswerEntity, Long> {
}
