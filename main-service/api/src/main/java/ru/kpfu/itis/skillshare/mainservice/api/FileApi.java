package ru.kpfu.itis.skillshare.mainservice.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.skillshare.mainservice.dto.response.UrlResponse;

import java.io.IOException;

@Tag(name = "Files | Файлы", description = "API файлов")
@RequestMapping("/api/v1/files")
public interface FileApi {

    @Operation(
            summary = "Сохранить изображение",
            description = "Позволяет сохранить новое изображение"
    )
    @SecurityRequirement(name = "JWT")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/upload/image")
    UrlResponse postImage(@RequestParam("upload") MultipartFile image) throws IOException;
}
