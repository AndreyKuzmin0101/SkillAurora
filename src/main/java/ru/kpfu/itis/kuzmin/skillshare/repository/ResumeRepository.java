package ru.kpfu.itis.kuzmin.skillshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.kuzmin.skillshare.model.ResumeEntity;

@Repository
public interface ResumeRepository extends JpaRepository<ResumeEntity, Long> {
}
