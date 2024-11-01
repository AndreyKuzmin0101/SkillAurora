package ru.skillaurora.articleservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skillaurora.articleservice.dto.request.TagRequest;
import ru.skillaurora.articleservice.dto.response.TagResponse;
import ru.skillaurora.articleservice.repository.TagRepository;
import ru.skillaurora.articleservice.service.TagService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public List<TagResponse> getSortedByCntUsage(Integer count) {
        return List.of();
    }

    @Override
    public List<TagResponse> search(String query, Integer maxCountMatches) {
        return List.of();
    }

    @Override
    public List<TagResponse> getAll(List<Long> ids) {
        return List.of();
    }

    @Override
    public List<Long> saveAll(List<TagRequest> tags) {
        return List.of();
    }
}
