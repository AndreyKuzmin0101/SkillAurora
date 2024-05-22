package ru.kpfu.itis.kuzmin.skillshare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kpfu.itis.kuzmin.skillshare.security.util.SecurityUtil;
import ru.kpfu.itis.kuzmin.skillshare.service.UserService;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;

    @GetMapping("/profile")
    public String getProfile() {
        return "profile";
    }

    @GetMapping("/users/{id}")
    public String getProfileSomeUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getProfileById(id));
        return "user-profile";
    }
}
