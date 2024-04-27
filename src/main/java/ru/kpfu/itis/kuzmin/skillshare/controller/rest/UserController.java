package ru.kpfu.itis.kuzmin.skillshare.controller.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.UserRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.UserResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.model.UserEntity;
import ru.kpfu.itis.kuzmin.skillshare.service.UserService;
import ru.kpfu.itis.kuzmin.skillshare.utils.SecurityUtil;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto getById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    //TODO: пагинация
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponseDto> getAll() {
        return userService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody UserRequestDto userDto) {
        return userService.save(userDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody UserRequestDto userDto) {
        userService.update(id, userDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/me/profile-image")
    public String getCurrentUserProfileImage() {
        UserEntity user = SecurityUtil.getAuthenticatedUser();
        return user.getProfileImage();
    }
}
