package ru.skillaurora.profileservice.mapper;

import org.mapstruct.Mapper;
import ru.skillaurora.profileservice.dto.response.CountryResponse;
import ru.skillaurora.profileservice.model.jooq.schema.tables.pojos.CountryEntity;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryResponse toResponse(CountryEntity entity);
}
