package ru.skillaurora.articleservice.controller.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.skillaurora.articleservice.api.internal.InternalTagApi;
import ru.skillaurora.articleservice.dto.request.TagRequest;
import ru.skillaurora.articleservice.dto.response.TagResponse;
import ru.skillaurora.articleservice.service.TagService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InternalTagController implements InternalTagApi {

    private final TagService tagService;

    @Override
    public List<TagResponse> getAllByIds(List<Long> ids) {
        return tagService.getAll(ids);
    }

    @Override
    public List<Long> saveAll(List<TagRequest> tags) {
        return tagService.saveAll(tags);
    }
}
