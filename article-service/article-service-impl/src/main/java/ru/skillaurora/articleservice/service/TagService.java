package ru.skillaurora.articleservice.service;


import ru.skillaurora.articleservice.dto.request.TagRequest;
import ru.skillaurora.articleservice.dto.response.TagResponse;

import java.util.List;

public interface TagService {

    List<TagResponse> getSortedByCntUsage(Integer count);

    List<TagResponse> search(String query, Integer maxCountMatches);

    List<TagResponse> getAll(List<Long> ids);

    List<Long> saveAll(List<TagRequest> tags);
}
