package ru.kpfu.itis.kuzmin.skillshare.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.UserRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.UserResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.model.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "skills", ignore = true)
    UserEntity toEntity(UserRequestDto userRequest);

    UserResponseDto toResponse(UserEntity userEntity);
}
