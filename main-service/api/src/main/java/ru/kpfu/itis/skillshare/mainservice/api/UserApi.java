package ru.kpfu.itis.skillshare.mainservice.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.skillshare.mainservice.dto.request.UserRequestDto;
import ru.kpfu.itis.skillshare.mainservice.dto.response.UserResponseDto;

import java.util.List;

@Tag(name = "Users | Пользователи", description = "API пользователей")
@RequestMapping("/api/v1/users")
public interface UserApi {

    @Operation(
            summary = "Получить пользователя по id",
            description = "Позволяет админу получить данные пользователя"
    )
    @SecurityRequirement(name = "JWT")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    UserResponseDto getById(@PathVariable("id") Long id);


    //TODO: пагинация
    @Operation(
            summary = "Получить часть всех пользователей",
            description = "Позволяет админу получить часть списка всех пользователей"
    )
    @SecurityRequirement(name = "JWT")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<UserResponseDto> getAll();

    @Operation(
            summary = "Создать пользователя",
            description = "Позволяет зарегестрироваться"
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Long create(@RequestBody UserRequestDto userDto);

    @Operation(
            summary = "Обновить пользователя",
            description = "Позволяет админу обновить пользователя"
    )
    @SecurityRequirement(name = "JWT")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void update(@PathVariable("id") Long id, @RequestBody UserRequestDto userDto);

    @Operation(
            summary = "Удалить пользователя",
            description = "Позволяет админу пометить пользователя удалённым"
    )
    @SecurityRequirement(name = "JWT")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long id);

    @Operation(
            summary = "Получить мой профиль",
            description = "Позволяет пользователю получить данные его профиля"
    )
    @SecurityRequirement(name = "JWT")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/me/profile")
    UserResponseDto getProfileData();

    @Operation(
            summary = "Получить мою аватарку",
            description = "Позволяет пользователю получить его аватарку"
    )
    @SecurityRequirement(name = "JWT")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/me/profile-image")
    String getCurrentUserProfileImage();
}
