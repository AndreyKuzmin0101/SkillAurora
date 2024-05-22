package ru.kpfu.itis.kuzmin.skillshare.repository.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.kuzmin.skillshare.model.ChatEntity;

import java.util.List;

@Repository
public interface ChatSpringRepository extends JpaRepository<ChatEntity, Long> {

    @Query("select c from ChatEntity c where c.firstUser.id = :userId or c.secondUser.id = :userId")
    List<ChatEntity> findByUserId(@Param("userId") Long userId);
}
