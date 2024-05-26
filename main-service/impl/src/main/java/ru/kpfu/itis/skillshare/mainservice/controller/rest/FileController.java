package ru.kpfu.itis.skillshare.mainservice.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.skillshare.mainservice.api.FileApi;
import ru.kpfu.itis.skillshare.mainservice.dto.response.UrlResponse;
import ru.kpfu.itis.skillshare.mainservice.service.FileService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class FileController implements FileApi {
    private final FileService fileService;

    @Override
    public UrlResponse postImage(MultipartFile image) throws IOException {
        return new UrlResponse(fileService.uploadImage(image));
    }

}
