package ru.kpfu.itis.kuzmin.skillshare.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.AnswerRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.VacancyRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.AnswerResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.VacancyResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.model.AnswerEntity;
import ru.kpfu.itis.kuzmin.skillshare.model.VacancyEntity;

@Mapper(componentModel = "spring")
public interface VacancyMapper {
    @Mapping(target = "id", ignore = true)
    VacancyEntity toEntity(VacancyRequestDto vacancyRequest);

    VacancyResponseDto toResponse(VacancyEntity vacancyEntity);
}
