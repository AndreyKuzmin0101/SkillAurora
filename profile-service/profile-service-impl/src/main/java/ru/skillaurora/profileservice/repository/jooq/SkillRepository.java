package ru.skillaurora.profileservice.repository.jooq;

import ru.skillaurora.profileservice.model.jooq.schema.tables.pojos.SkillEntity;

public interface SkillRepository {

    void saveAll(Iterable<SkillEntity> skills);
}
