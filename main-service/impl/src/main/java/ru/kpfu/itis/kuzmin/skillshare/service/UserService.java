package ru.kpfu.itis.kuzmin.skillshare.service;

import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.UserRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.TagResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.UserResponseDto;

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
