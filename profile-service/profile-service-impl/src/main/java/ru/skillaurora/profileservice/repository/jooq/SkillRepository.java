package ru.skillaurora.profileservice.repository.jooq;

import ru.skillaurora.profileservice.model.jooq.schema.tables.pojos.SkillEntity;

import java.util.UUID;

public interface SkillRepository {

    void saveAll(Iterable<SkillEntity> skills);

    void deleteAllByUserId(UUID userId);
}
