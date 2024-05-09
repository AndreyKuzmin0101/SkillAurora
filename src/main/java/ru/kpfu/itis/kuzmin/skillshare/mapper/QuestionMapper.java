package ru.kpfu.itis.kuzmin.skillshare.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.QuestionRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.UserRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.QuestionResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.UserResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.model.QuestionEntity;
import ru.kpfu.itis.kuzmin.skillshare.model.UserEntity;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tags", ignore = true)
    QuestionEntity toEntity(QuestionRequestDto questionRequest);

    QuestionResponseDto toResponse(QuestionEntity questionEntity);
}
