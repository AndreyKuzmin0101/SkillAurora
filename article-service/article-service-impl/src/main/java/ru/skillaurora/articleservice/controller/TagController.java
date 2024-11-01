package ru.skillaurora.articleservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.skillaurora.articleservice.api.TagApi;
import ru.skillaurora.articleservice.dto.response.TagResponse;
import ru.skillaurora.articleservice.service.TagService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TagController implements TagApi {

    private final TagService tagService;

    @Override
    public List<TagResponse> getMostPopular(Integer count) {
        return tagService.getSortedByCntUsage(count);
    }

    @Override
    public List<TagResponse> searchTags(String query, Integer maxCountMatches) {
        return tagService.search(query, maxCountMatches);
    }
}
