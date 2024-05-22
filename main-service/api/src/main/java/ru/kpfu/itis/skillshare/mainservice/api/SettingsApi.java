package ru.kpfu.itis.skillshare.mainservice.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.skillshare.mainservice.dto.request.UserRequestDto;
import ru.kpfu.itis.skillshare.mainservice.dto.response.UrlResponse;

import java.io.IOException;

@Tag(name = "User Settings | Настройки пользователя", description = "API настроек пользователя")
@RequestMapping("/api/v1/users/settings")
public interface SettingsApi {

    @Operation(
            summary = "Обновить одно поле",
            description = "Позволяет обновить один параметр пользователя"
    )
    @SecurityRequirement(name = "JWT")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    void updateOneField(@RequestBody UserRequestDto userRequestDto);

    @Operation(
            summary = "Обновить аватарку профиля",
            description = "Позволяет обновить аватарку профиля пользователя"
    )
    @SecurityRequirement(name = "JWT")
    @PutMapping("/profile-image")
    @ResponseStatus(HttpStatus.OK)
    UrlResponse updateProfileImage(@RequestParam("upload") MultipartFile image) throws IOException;
}
