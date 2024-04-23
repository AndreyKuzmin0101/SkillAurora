package ru.kpfu.itis.kuzmin.skillshare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.kuzmin.skillshare.aspect.annotation.Loggable;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.UrlResponse;
import ru.kpfu.itis.kuzmin.skillshare.service.FileService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @Loggable
    @PostMapping("/upload/image")
    public UrlResponse postImage(@RequestParam("upload") MultipartFile image) throws IOException {
        return new UrlResponse(fileService.uploadImage(image, "TEST"));
    }

}
