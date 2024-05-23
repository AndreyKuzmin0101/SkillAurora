package ru.kpfu.itis.skillshare.mainservice.repository.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.skillshare.mainservice.model.QuestionEntity;

@Repository
@Transactional
public interface QuestionSpringRepository extends JpaRepository<QuestionEntity, Long> {

    @Modifying
    @Query("update QuestionEntity q set q.views = q.views + 1 where q.id = :questionId")
    void increaseViews(@Param("questionId") Long questionId);
}
