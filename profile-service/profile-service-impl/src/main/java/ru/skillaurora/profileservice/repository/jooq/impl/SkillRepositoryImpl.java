package ru.skillaurora.profileservice.repository.jooq.impl;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Query;
import org.springframework.stereotype.Repository;
import ru.skillaurora.profileservice.model.jooq.schema.tables.pojos.SkillEntity;
import ru.skillaurora.profileservice.repository.jooq.SkillRepository;

import java.util.ArrayList;
import java.util.List;

import static ru.skillaurora.profileservice.model.jooq.schema.tables.Skill.SKILL_ENTITY;

@Repository
@RequiredArgsConstructor
public class SkillRepositoryImpl implements SkillRepository {

    private final DSLContext dsl;

    @Override
    public void saveAll(Iterable<SkillEntity> skills) {
        List<Query> queries = new ArrayList<>();
        for (SkillEntity entity : skills) {
            queries.add(
                    dsl.insertInto(SKILL_ENTITY)
                            .set(dsl.newRecord(SKILL_ENTITY, entity))
            );
        }

        dsl.batch(queries).execute();
    }
}
