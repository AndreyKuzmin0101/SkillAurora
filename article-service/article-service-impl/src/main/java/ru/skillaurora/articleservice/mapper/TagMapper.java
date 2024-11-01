package ru.skillaurora.articleservice.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skillaurora.articleservice.dto.request.TagRequest;
import ru.skillaurora.articleservice.dto.response.ArticleResponse;
import ru.skillaurora.articleservice.dto.response.TagResponse;
import ru.skillaurora.articleservice.model.ArticleEntity;
import ru.skillaurora.articleservice.model.TagEntity;

@Mapper(componentModel = "spring")
public interface TagMapper {

    @Mapping(target = "id", ignore = true)
    TagEntity toEntity(TagRequest articleRequest);

    TagResponse toResponse(TagEntity tagEntity);
}