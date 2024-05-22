package ru.kpfu.itis.skillshare.mainservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.kpfu.itis.skillshare.mainservice.dto.request.ArticleRequestDto;
import ru.kpfu.itis.skillshare.mainservice.dto.response.ArticleResponseDto;
import ru.kpfu.itis.skillshare.mainservice.model.ArticleEntity;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tags", ignore = true)
    ArticleEntity toEntity(ArticleRequestDto articleRequest);

    ArticleResponseDto toResponse(ArticleEntity articleEntity);
}
