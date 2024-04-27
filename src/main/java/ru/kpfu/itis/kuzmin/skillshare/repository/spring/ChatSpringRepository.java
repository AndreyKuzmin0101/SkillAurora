package ru.kpfu.itis.kuzmin.skillshare.repository.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.kuzmin.skillshare.model.ChatEntity;

@Repository
public interface ChatSpringRepository extends JpaRepository<ChatEntity, Long> {
}
