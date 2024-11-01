package ru.skillaurora.articleservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skillaurora.articleservice.dto.request.ArticleRequest;
import ru.skillaurora.articleservice.dto.response.ArticleResponse;
import ru.skillaurora.articleservice.model.ArticleEntity;

@Mapper(componentModel = "spring", uses = {TagMapper.class})
public interface ArticleMapper {

    @Mapping(target = "id", ignore = true)
    ArticleEntity toEntity(ArticleRequest articleRequest);

    ArticleResponse toResponse(ArticleEntity articleEntity);
}
