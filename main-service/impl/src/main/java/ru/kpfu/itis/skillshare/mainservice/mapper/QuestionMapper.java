package ru.kpfu.itis.skillshare.mainservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.kpfu.itis.skillshare.mainservice.dto.request.QuestionRequestDto;
import ru.kpfu.itis.skillshare.mainservice.dto.response.QuestionResponseDto;
import ru.kpfu.itis.skillshare.mainservice.model.QuestionEntity;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tags", ignore = true)
    QuestionEntity toEntity(QuestionRequestDto questionRequest);

    QuestionResponseDto toResponse(QuestionEntity questionEntity);
}
