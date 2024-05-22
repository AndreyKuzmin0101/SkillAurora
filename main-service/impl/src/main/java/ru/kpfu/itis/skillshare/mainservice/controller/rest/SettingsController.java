package ru.kpfu.itis.skillshare.mainservice.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.skillshare.mainservice.api.SettingsApi;
import ru.kpfu.itis.skillshare.mainservice.dto.request.UserRequestDto;
import ru.kpfu.itis.skillshare.mainservice.dto.response.UrlResponse;
import ru.kpfu.itis.skillshare.mainservice.security.util.SecurityUtil;
import ru.kpfu.itis.skillshare.mainservice.service.UserService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class SettingsController implements SettingsApi {
    private final UserService userService;

    @Override
    public void updateOneField(UserRequestDto userRequestDto) {
        Long id = SecurityUtil.getIdAuthenticatedUser();
        userService.updateNotNullFields(id, userRequestDto);
    }

    @Override
    public UrlResponse updateProfileImage(MultipartFile image) throws IOException {
        return new UrlResponse(userService.updateProfileImage(image));
    }
}
