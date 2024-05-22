package ru.kpfu.itis.skillshare.mainservice.repository.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.skillshare.mainservice.model.MessageEntity;

import java.util.List;

@Repository
public interface MessageSpringRepository extends JpaRepository<MessageEntity, Long> {

    @Query("select c.messages from ChatEntity c where c.id = :chatId")
    List<MessageEntity> findAllByChatId(@Param("chatId") Long chatId);
}
