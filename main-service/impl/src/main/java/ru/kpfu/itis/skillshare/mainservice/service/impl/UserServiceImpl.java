package ru.kpfu.itis.skillshare.mainservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.skillshare.mainservice.dto.Roles;
import ru.kpfu.itis.skillshare.mainservice.dto.request.UserRequestDto;
import ru.kpfu.itis.skillshare.mainservice.dto.response.UserResponseDto;
import ru.kpfu.itis.skillshare.mainservice.exception.alreadyexitsts.UserWithEmailAlreadyExistsException;
import ru.kpfu.itis.skillshare.mainservice.exception.alreadyexitsts.UserWithNameAlreadyExistsException;
import ru.kpfu.itis.skillshare.mainservice.exception.notfound.UserNotFoundException;
import ru.kpfu.itis.skillshare.mainservice.mapper.UserMapper;
import ru.kpfu.itis.skillshare.mainservice.model.TagEntity;
import ru.kpfu.itis.skillshare.mainservice.model.UserEntity;
import ru.kpfu.itis.skillshare.mainservice.repository.jpa.UserJpaRepository;
import ru.kpfu.itis.skillshare.mainservice.repository.spring.RoleSpringRepository;
import ru.kpfu.itis.skillshare.mainservice.repository.spring.UserSpringRepository;
import ru.kpfu.itis.skillshare.mainservice.service.FileService;
import ru.kpfu.itis.skillshare.mainservice.service.TagService;
import ru.kpfu.itis.skillshare.mainservice.service.UserService;
import ru.kpfu.itis.skillshare.mainservice.security.util.SecurityUtil;
import ru.kpfu.itis.skillshare.mainservice.utils.UserProfileUtil;

import java.io.IOException;
import java.sql.Date;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final FileService fileService;
    private final TagService tagService;

    private final UserSpringRepository userSpringRepository;
    private final UserJpaRepository userJpaRepository;
    private final RoleSpringRepository roleSpringRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

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
    public UserResponseDto getProfileById(Long id) {
        Optional<UserEntity> optionalUser = userSpringRepository.findById(id);
        if (optionalUser.isPresent()) {
            UserEntity user = UserProfileUtil.processUser(optionalUser.get());
            return userMapper.toResponse(user);
        }
        throw new UserNotFoundException(id);
    }

    @Override
    public Long save(UserRequestDto userDto) {
        if (userSpringRepository.findByEmail(userDto.email()).isPresent()) {
            throw new UserWithEmailAlreadyExistsException(userDto.email());
        };

        if (userSpringRepository.findByUsername(userDto.username()).isPresent()) {
            throw new UserWithNameAlreadyExistsException(userDto.username());
        }

        UserEntity user = userMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRegisterDate(new Date(System.currentTimeMillis()));
        user.setRating(0);
        user.setEnabled(true);
        user.setProfileImage("https://res.cloudinary.com/debjgvnym/image/upload/v1713967470/user.jpg");

        user.setSkills(tagService.getListUniqTagEntitiesAndSaveNonExistent(userDto.skills()));

        user.setRoles(List.of(
                roleSpringRepository.findByName(Roles.USER.getFullName()).get()
        ));

        return userSpringRepository
                .save(user)
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

        updatedUser.setSkills(tagService.getListUniqTagEntitiesAndSaveNonExistent(userDto.skills()));

        updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));

        userJpaRepository.update(updatedUser);
    }

    @Override
    public void updateNotNullFields(Long id, UserRequestDto userDto) {
        Optional<UserEntity> optionalUser = userSpringRepository.findById(id);
        UserEntity user;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            throw new UserNotFoundException(id);
        }

        String username;
        if (userDto.username() == null) {
            username = user.getUsername();
        } else {
            if (!user.getUsername().equals(userDto.username())) {
                if (userSpringRepository.findByUsername(userDto.username()).isPresent()) {
                    throw new UserWithNameAlreadyExistsException(userDto.username());
                }
            }
            username = userDto.username();
        }
        String realName = userDto.realName() == null ? user.getRealName() : userDto.realName();
        Integer age = userDto.age() == null ? user.getAge() : userDto.age();
        String email;
        if (userDto.email() == null) {
            email = user.getEmail();
        } else {
            if (!user.getEmail().equals(userDto.email())) {
                if (userSpringRepository.findByEmail(userDto.email()).isPresent()) {
                    throw new UserWithEmailAlreadyExistsException(userDto.email());
                }
            }
            email = userDto.email();
        }
        String country = userDto.country() == null ? user.getCountry() : userDto.country();
        String city = userDto.city() == null ? user.getCity() : userDto.city();
        String password = userDto.password() == null ? user.getPassword() : passwordEncoder.encode(userDto.password());
        String profileImage = userDto.profileImage() == null ? user.getProfileImage() : userDto.profileImage();

        List<TagEntity> skills = user.getSkills();
        if (userDto.skills() != null) {
            skills = tagService.getListUniqTagEntitiesAndSaveNonExistent(userDto.skills());
        }

        UserEntity updatedUser = UserEntity.builder()
                .id(id)
                .username(username)
                .age(age)
                .realName(realName)
                .city(city)
                .country(country)
                .email(email)
                .skills(skills)
                .password(password)
                .profileImage(profileImage)
                .roles(user.getRoles())
                .rating(user.getRating())
                .registerDate(user.getRegisterDate())
                .enabled(user.getEnabled())
                .build();
        userJpaRepository.update(updatedUser);
    }

    @Override
    public String updateProfileImage(MultipartFile image) throws IOException {
        String url = fileService.uploadImage(image);
        Long userId = SecurityUtil.getIdAuthenticatedUser();
        userSpringRepository.updateProfileImage(userId, url);
        return url;
    }

    // В целях сохранения целостности данных пользователь просто помечается удалённым
    @Override
    public void deleteUser(Long id) {
        Optional<UserEntity> optionalUser = userSpringRepository.findById(id);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            user.setEnabled(false);
            userSpringRepository.save(user);
        } else {
            throw new UserNotFoundException(id);
        }
    }
}
