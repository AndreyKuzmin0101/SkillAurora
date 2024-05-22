package ru.kpfu.itis.kuzmin.skillshare.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.AnswerRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.UserRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.AnswerResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.UserResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.model.AnswerEntity;
import ru.kpfu.itis.kuzmin.skillshare.model.UserEntity;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    @Mapping(target = "id", ignore = true)
    AnswerEntity toEntity(AnswerRequestDto answerRequest);

    AnswerResponseDto toResponse(AnswerEntity answerEntity);
}
