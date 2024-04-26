package ru.kpfu.itis.kuzmin.skillshare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kpfu.itis.kuzmin.skillshare.security.BaseUserDetails;
import ru.kpfu.itis.kuzmin.skillshare.service.UserService;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;

    @GetMapping("/profile")
    public String redirectToProfile() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = ((BaseUserDetails) principal).getUser().getId();
        return "redirect:/users/%s".formatted(userId);
    }

    @GetMapping("/users/{id}")
    public String getProfile(@PathVariable("id") Long id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() &&
                authentication.getPrincipal() instanceof BaseUserDetails) {
            Long userId = ((BaseUserDetails) authentication.getPrincipal()).getUser().getId();
            if (userId.equals(id)) {
                model.addAttribute("user", userService.getUserById(id));
                return "profile";
            }
        }
        model.addAttribute("user", userService.getProfileById(id));
        return "user-profile";
    }
}
