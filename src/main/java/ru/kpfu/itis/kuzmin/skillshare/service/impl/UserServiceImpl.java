package ru.kpfu.itis.kuzmin.skillshare.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.UserRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.exception.alreadyexitsts.UserAlreadyExistsException;
import ru.kpfu.itis.kuzmin.skillshare.mapper.UserMapper;
import ru.kpfu.itis.kuzmin.skillshare.repository.UserRepository;
import ru.kpfu.itis.kuzmin.skillshare.service.FileService;
import ru.kpfu.itis.kuzmin.skillshare.service.UserService;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final FileService fileService;

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Long save(UserRequestDto userDto) {
        if (userRepository.findByEmail(userDto.email()).isPresent()) {
            throw new UserAlreadyExistsException(userDto.email());
        };

        return userRepository
                .save(userMapper.toEntity(userDto))
                .getId();
    }

    @Override
    public String updateProfileImage(MultipartFile image, String username) throws IOException {
        String url = fileService.uploadImage(image, username);
        // TODO: обновляем базу
        return url;
    }
}
