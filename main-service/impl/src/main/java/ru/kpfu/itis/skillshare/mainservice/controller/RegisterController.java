package ru.kpfu.itis.skillshare.mainservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    @GetMapping("/register")
    public String getRegisterForm() {
        return "register";
    }
}
