package ru.kpfu.itis.kuzmin.skillshare.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.UserRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.UrlResponse;
import ru.kpfu.itis.kuzmin.skillshare.security.util.SecurityUtil;
import ru.kpfu.itis.kuzmin.skillshare.service.UserService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/settings")
public class SettingsController {
    private final UserService userService;

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateOneField(@RequestBody UserRequestDto userRequestDto) {
        Long id = SecurityUtil.getIdAuthenticatedUser();
        userService.updateNotNullFields(id, userRequestDto);
    }

    @PutMapping("/profile-image")
    public UrlResponse updateProfileImage(@RequestParam("upload") MultipartFile image) throws IOException {
        return new UrlResponse(userService.updateProfileImage(image));
    }
}
