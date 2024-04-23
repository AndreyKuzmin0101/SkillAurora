package ru.kpfu.itis.kuzmin.skillshare.repository.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.kuzmin.skillshare.model.TagEntity;

import java.util.Optional;

@Repository
public interface TagSpringRepository extends JpaRepository<TagEntity, Integer> {

    Optional<TagEntity> findByName(String name);
}
