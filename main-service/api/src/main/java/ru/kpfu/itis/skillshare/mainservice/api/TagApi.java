package ru.kpfu.itis.skillshare.mainservice.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.skillshare.mainservice.dto.response.TagResponseDto;

import java.util.List;

@Tag(name = "Tags | Теги", description = "API тегов")
@RequestMapping("/api/v1/tags")
public interface TagApi {

    @Operation(
            summary = "Получить стандартные теги",
            description = "Позволяет получить стандартные теги, созданные не пользователем"
    )
    @GetMapping("/not-custom")
    List<TagResponseDto> getAllNotCustom();

}
