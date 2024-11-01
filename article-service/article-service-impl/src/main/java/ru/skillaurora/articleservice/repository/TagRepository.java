package ru.skillaurora.articleservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillaurora.articleservice.model.TagEntity;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long> {
}
