package ru.skillaurora.profileservice.client.stub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.skillaurora.profileservice.client.ArticleServiceClient;
import ru.skillaurora.profileservice.dto.request.TagRequest;
import ru.skillaurora.profileservice.dto.response.SkillResponse;

import java.util.List;

@Component
@Profile("local")
@Slf4j
public class ArticleServiceClientStub implements ArticleServiceClient {

    @Override
    public List<SkillResponse> getAllByIds(List<Long> ids) {
        log.info("ОБРАЩЕНИЕ К СЕРВИСУ СТАТЕЙ...");
        return List.of(new SkillResponse(1L, "Java"), new SkillResponse(2L, "Spring"));
    }

    @Override
    public List<Long> saveAll(List<TagRequest> tags) {
        log.info("ОБРАЩЕНИЕ К СЕРВИСУ СТАТЕЙ...");
        return List.of(1L, 2L, 3L);
    }
}
