package ru.kpfu.itis.skillshare.mainservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.kpfu.itis.skillshare.mainservice.dto.request.AnswerRequestDto;
import ru.kpfu.itis.skillshare.mainservice.dto.response.AnswerResponseDto;
import ru.kpfu.itis.skillshare.mainservice.model.AnswerEntity;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    @Mapping(target = "id", ignore = true)
    AnswerEntity toEntity(AnswerRequestDto answerRequest);

    AnswerResponseDto toResponse(AnswerEntity answerEntity);
}
