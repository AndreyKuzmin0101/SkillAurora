package ru.kpfu.itis.skillshare.mainservice.repository.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.skillshare.mainservice.model.TagEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagSpringRepository extends JpaRepository<TagEntity, Integer> {

    Optional<TagEntity> findByName(String name);

    List<TagEntity> findAllByCustom(Boolean isCustom);
}
