package ru.kpfu.itis.kuzmin.skillshare.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.kuzmin.skillshare.aspect.annotation.Loggable;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.UrlResponse;
import ru.kpfu.itis.kuzmin.skillshare.service.FileService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
public class FileController {
    private final FileService fileService;

    @Loggable
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/upload/image")
    public UrlResponse postImage(@RequestParam("upload") MultipartFile image) throws IOException {
        return new UrlResponse(fileService.uploadImage(image));
    }

}
