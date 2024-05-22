package ru.kpfu.itis.skillshare.mainservice.repository.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.skillshare.mainservice.model.VacancyEntity;

@Repository
public interface VacancySpringRepository extends JpaRepository<VacancyEntity, Long> {
}
