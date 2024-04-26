package ru.kpfu.itis.kuzmin.skillshare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.UserRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.service.UserService;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    @GetMapping("/register")
    public String getRegisterForm() {
        return "register";
    }
}
