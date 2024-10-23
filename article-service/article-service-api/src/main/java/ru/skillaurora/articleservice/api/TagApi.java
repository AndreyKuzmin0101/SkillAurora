package ru.skillaurora.articleservice.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.skillaurora.articleservice.dto.response.TagResponse;

import java.util.List;

@Tag(name = "Tags API | API тегов", description = "API системы тегов")
@RequestMapping("/api/v2/tags")
public interface TagApi {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<TagResponse> getMostPopular(@RequestParam(value = "count", required = false, defaultValue = "20") Integer count);

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    List<TagResponse> searchTags(@RequestParam(value = "query", required = true) String query,
                                 @RequestParam(value = "max_count_matches", required = false, defaultValue = "20")
                                 Integer maxCountMatches);
}
