package ru.kpfu.itis.skillshare.mainservice.service.impl;

import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.skillshare.mainservice.model.UserEntity;
import ru.kpfu.itis.skillshare.mainservice.repository.spring.UserSpringRepository;
import ru.kpfu.itis.skillshare.mainservice.service.FileService;
import ru.kpfu.itis.skillshare.mainservice.utils.CloudinaryUtil;
import ru.kpfu.itis.skillshare.mainservice.security.util.SecurityUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final UserSpringRepository userSpringRepository;

    private final CloudinaryUtil cloudinaryUtil;

    @Override
    public String uploadImage(MultipartFile image) throws IOException {
        UserEntity currentUser = userSpringRepository.findById(SecurityUtil.getIdAuthenticatedUser()).get();
        String username = currentUser.getUsername();

        long currentTimeMillis = System.currentTimeMillis();
        String filename = image.getOriginalFilename();

        File file = File.createTempFile(username + File.separator
                + currentTimeMillis + File.separator + filename, "");

        try (InputStream content = image.getInputStream()) {
            try (FileOutputStream outputStream = new FileOutputStream(file)) {
                byte[] buffer = new byte[content.available()];
                content.read(buffer);
                outputStream.write(buffer);
            }
        }

        String filenameWithoutType = removeTypeFile(filename);
        String imagePath = "%s/%s/".formatted(username, currentTimeMillis);

        cloudinaryUtil.getInstance().uploader().upload(file, ObjectUtils.asMap("public_id", imagePath + filenameWithoutType));
        return "https://res.cloudinary.com/debjgvnym/image/upload/" + imagePath + filename;
    }



    private String removeTypeFile(String filename) {
        if (filename.endsWith(".png") || filename.endsWith(".jpg")) {
            filename = filename.substring(0, filename.length()-4);
        } else if (filename.endsWith(".jpeg")) {
            filename = filename.substring(0, filename.length()-5);
        }
        return filename;
    }
}
