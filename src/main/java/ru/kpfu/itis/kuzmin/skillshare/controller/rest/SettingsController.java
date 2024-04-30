package ru.kpfu.itis.kuzmin.skillshare.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.UserRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.UrlResponse;
import ru.kpfu.itis.kuzmin.skillshare.security.BaseUserDetails;
import ru.kpfu.itis.kuzmin.skillshare.service.UserService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class SettingsController {
    private final UserService userService;

    @PutMapping("/settings")
    @ResponseStatus(HttpStatus.OK)
    public void updateOneField(@RequestBody UserRequestDto userRequestDto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = ((BaseUserDetails) principal).getUser().getId();
        userService.updateNotNullFields(id, userRequestDto);
    }

    @PutMapping("/settings/profile-image")
    public UrlResponse updateProfileImage(@RequestParam("upload") MultipartFile image) throws IOException {
        return new UrlResponse(userService.updateProfileImage(image));
    }
}
