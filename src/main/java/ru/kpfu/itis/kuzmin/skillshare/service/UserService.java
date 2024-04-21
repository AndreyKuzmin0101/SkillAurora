package ru.kpfu.itis.kuzmin.skillshare.service;

import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.UserRequestDto;

import java.io.IOException;

public interface UserService {
    Long save(UserRequestDto userDto);

    String updateProfileImage(MultipartFile image, String username) throws IOException;
}
