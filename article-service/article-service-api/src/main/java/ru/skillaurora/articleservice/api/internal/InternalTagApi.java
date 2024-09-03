package ru.skillaurora.articleservice.api.internal;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.skillaurora.articleservice.dto.request.TagsRequest;
import ru.skillaurora.articleservice.dto.response.TagResponse;

import java.util.List;

@Hidden
@RequestMapping("/internal/api/v2/article-service/tags")
public interface InternalTagApi {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<TagResponse> getAllByIds(List<Long> ids);

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    List<Long> saveNonExistentAndGetAllIds(TagsRequest tags);
}
