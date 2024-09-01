package ru.skillaurora.profileservice.repository.jooq.impl;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.skillaurora.profileservice.model.jooq.schema.tables.pojos.CountryEntity;
import ru.skillaurora.profileservice.repository.jooq.CountryRepository;

import java.util.Optional;

import static ru.skillaurora.profileservice.model.jooq.schema.tables.Country.COUNTRY_ENTITY;

@Repository
@RequiredArgsConstructor
public class CountryRepositoryImpl implements CountryRepository {

    private final DSLContext dsl;

    @Override
    public Optional<CountryEntity> findByCode(String code) {
        return dsl.selectFrom(COUNTRY_ENTITY)
                .where(COUNTRY_ENTITY.CODE.eq(code))
                .fetchOptionalInto(CountryEntity.class);
    }
}
