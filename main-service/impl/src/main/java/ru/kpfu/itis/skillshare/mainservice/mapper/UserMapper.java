package ru.kpfu.itis.skillshare.mainservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.kpfu.itis.skillshare.mainservice.dto.request.UserRequestDto;
import ru.kpfu.itis.skillshare.mainservice.dto.response.UserResponseDto;
import ru.kpfu.itis.skillshare.mainservice.model.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "skills", ignore = true)
    UserEntity toEntity(UserRequestDto userRequest);

    UserResponseDto toResponse(UserEntity userEntity);
}
