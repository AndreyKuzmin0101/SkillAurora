package ru.kpfu.itis.kuzmin.skillshare.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String uploadImage(MultipartFile image, String username) throws IOException;
}
