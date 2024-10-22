package ru.skillaurora.profileservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.skillaurora.profileservice.dto.request.TagRequest;
import ru.skillaurora.profileservice.dto.response.SkillResponse;

import java.util.List;


@FeignClient(name = "article-service")
@Profile("!local")
public interface ArticleServiceClient {

    @GetMapping("/internal/api/v2/article-service/tags")
    List<SkillResponse> getAllByIds(List<Long> ids);

    @PostMapping("/internal/api/v2/article-service/tags")
    List<Long> saveAll(List<TagRequest> tags);
}
