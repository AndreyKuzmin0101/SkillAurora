package ru.kpfu.itis.skillshare.mainservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.kpfu.itis.skillshare.mainservice.dto.request.VacancyRequestDto;
import ru.kpfu.itis.skillshare.mainservice.dto.response.VacancyResponseDto;
import ru.kpfu.itis.skillshare.mainservice.model.VacancyEntity;

@Mapper(componentModel = "spring")
public interface VacancyMapper {
    @Mapping(target = "id", ignore = true)
    VacancyEntity toEntity(VacancyRequestDto vacancyRequest);

    VacancyResponseDto toResponse(VacancyEntity vacancyEntity);
}
