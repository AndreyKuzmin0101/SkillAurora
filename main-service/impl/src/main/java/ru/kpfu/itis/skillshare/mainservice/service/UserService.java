package ru.kpfu.itis.skillshare.mainservice.service;

import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.skillshare.mainservice.dto.request.UserRequestDto;
import ru.kpfu.itis.skillshare.mainservice.dto.response.UserResponseDto;

import java.io.IOException;
import java.util.List;

public interface UserService {
    UserResponseDto getUserById(Long id);

    Long save(UserRequestDto userDto);

    void update(Long id, UserRequestDto userDto);

    void updateNotNullFields(Long id, UserRequestDto userDto);

    String updateProfileImage(MultipartFile image) throws IOException;

    // TODO: с пользователем связаны статьи, теги, вопросы, ответы, что делать?
    void deleteUser(Long id);

    List<UserResponseDto> getAll();

    UserResponseDto getProfileById(Long id);
}
