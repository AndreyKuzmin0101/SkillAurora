package ru.skillaurora.articleservice.dto.request;

import jakarta.validation.Valid;
import ru.skillaurora.articleservice.validate.Tags;

import java.util.List;

@Tags
public record TagsRequest (
        List<Long> tagIds,
        @Valid
        List<NewTagRequest> newTags
) {
}
