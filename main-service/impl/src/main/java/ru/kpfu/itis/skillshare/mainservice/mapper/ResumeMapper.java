package ru.kpfu.itis.skillshare.mainservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.kpfu.itis.skillshare.mainservice.dto.request.ResumeRequestDto;
import ru.kpfu.itis.skillshare.mainservice.dto.response.ResumeResponseDto;
import ru.kpfu.itis.skillshare.mainservice.model.ResumeEntity;

@Mapper(componentModel = "spring")
public interface ResumeMapper {
    @Mapping(target = "id", ignore = true)
    ResumeEntity toEntity(ResumeRequestDto resumeRequest);

    ResumeResponseDto toResponse(ResumeEntity resumeEntity);
}
