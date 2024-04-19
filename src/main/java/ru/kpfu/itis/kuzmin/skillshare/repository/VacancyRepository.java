package ru.kpfu.itis.kuzmin.skillshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.kuzmin.skillshare.model.VacancyEntity;

@Repository
public interface VacancyRepository extends JpaRepository<VacancyEntity, Long> {
}
