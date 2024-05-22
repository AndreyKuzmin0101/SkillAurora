package ru.kpfu.itis.skillshare.mainservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.kpfu.itis.skillshare.mainservice.dto.request.TagRequestDto;
import ru.kpfu.itis.skillshare.mainservice.dto.response.TagResponseDto;
import ru.kpfu.itis.skillshare.mainservice.model.TagEntity;

@Mapper(componentModel = "spring")
public interface TagMapper {
    @Mapping(target = "id", ignore = true)
    TagEntity toEntity(TagRequestDto tagRequest);

    TagResponseDto toResponse(TagEntity tagEntity);
}
