package ru.kpfu.itis.kuzmin.skillshare.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.ResumeRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.UserRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.ResumeResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.UserResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.model.ResumeEntity;
import ru.kpfu.itis.kuzmin.skillshare.model.UserEntity;

@Mapper(componentModel = "spring")
public interface ResumeMapper {
    @Mapping(target = "id", ignore = true)
    ResumeEntity toEntity(ResumeRequestDto resumeRequest);

    ResumeResponseDto toResponse(ResumeEntity resumeEntity);
}
