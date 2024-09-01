package ru.skillaurora.profileservice.repository.jooq;

import ru.skillaurora.profileservice.model.jooq.schema.tables.pojos.CountryEntity;

import java.util.Optional;

public interface CountryRepository {

    Optional<CountryEntity> findByCode(String code);
}
