package ru.kpfu.itis.kuzmin.skillshare.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.UserRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.UserResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.exception.alreadyexitsts.UserWithEmailAlreadyExistsException;
import ru.kpfu.itis.kuzmin.skillshare.exception.alreadyexitsts.UserWithNameAlreadyExistsException;
import ru.kpfu.itis.kuzmin.skillshare.exception.notfound.UserNotFoundException;
import ru.kpfu.itis.kuzmin.skillshare.mapper.UserMapper;
import ru.kpfu.itis.kuzmin.skillshare.model.UserEntity;
import ru.kpfu.itis.kuzmin.skillshare.repository.jpa.UserJpaRepository;
import ru.kpfu.itis.kuzmin.skillshare.repository.spring.UserSpringRepository;
import ru.kpfu.itis.kuzmin.skillshare.service.FileService;
import ru.kpfu.itis.kuzmin.skillshare.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final FileService fileService;

    private final UserSpringRepository userSpringRepository;
    private final UserJpaRepository userJpaRepository;

    private final UserMapper userMapper;


    @Override
    public UserResponseDto getUserById(Long id) {
        Optional<UserEntity> optionalUser = userSpringRepository.findById(id);

        if (optionalUser.isPresent()) {
            return userMapper.toResponse(optionalUser.get());
        } else {
            throw new UserNotFoundException(id);
        }
    }

    @Override
    public List<UserResponseDto> getAll() {
        return userSpringRepository.findAll().stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    public Long save(UserRequestDto userDto) {
        if (userSpringRepository.findByEmail(userDto.email()).isPresent()) {
            throw new UserWithEmailAlreadyExistsException(userDto.email());
        };

        if (userSpringRepository.findByUsername(userDto.username()).isPresent()) {
            throw new UserWithNameAlreadyExistsException(userDto.username());
        }

        return userSpringRepository
                .save(userMapper.toEntity(userDto))
                .getId();
    }

    @Override
    public void update(Long id, UserRequestDto userDto) {
        UserEntity updatedUser = userMapper.toEntity(userDto);
        updatedUser.setId(id);

        Optional<UserEntity> optionalUser = userSpringRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(id);
        }

        UserEntity user = optionalUser.get();

        if (!user.getEmail().equals(updatedUser.getEmail())) {
            if (userSpringRepository.findByEmail(updatedUser.getEmail()).isPresent()) {
                throw new UserWithEmailAlreadyExistsException(updatedUser.getEmail());
            }
        }

        if (!user.getUsername().equals(updatedUser.getUsername())) {
            if (userSpringRepository.findByUsername(updatedUser.getUsername()).isPresent()) {
                throw new UserWithNameAlreadyExistsException(updatedUser.getUsername());
            }
        }

        userJpaRepository.update(updatedUser);
    }

    @Override
    public String updateProfileImage(MultipartFile image, String username) throws IOException {
        String url = fileService.uploadImage(image, username);
        // TODO: обновляем базу
        return url;
    }

    // TODO: с пользователем связаны статьи, теги, вопросы, ответы, что делать?
    @Override
    public void deleteUser(Long id) {

    }


}
