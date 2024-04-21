package ru.kpfu.itis.kuzmin.skillshare.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.kuzmin.skillshare.service.FileService;
import ru.kpfu.itis.kuzmin.skillshare.utils.CloudinaryUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FileServiceImpl implements FileService {
    private final Cloudinary cloudinary = CloudinaryUtil.getInstance();
    @Override
    public String uploadImage(MultipartFile image, String username) throws IOException {
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

        cloudinary.uploader().upload(file, ObjectUtils.asMap("public_id", imagePath + filenameWithoutType));
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
