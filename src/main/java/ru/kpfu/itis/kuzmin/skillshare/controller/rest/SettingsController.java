package ru.kpfu.itis.kuzmin.skillshare.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.UserRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.security.BaseUserDetails;
import ru.kpfu.itis.kuzmin.skillshare.service.UserService;

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
}
