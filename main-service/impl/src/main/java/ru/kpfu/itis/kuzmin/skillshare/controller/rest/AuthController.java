package ru.kpfu.itis.kuzmin.skillshare.controller.rest;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.kuzmin.skillshare.dto.Roles;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.LoginRequest;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.TokenRequest;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.TokenCoupleResponse;
import ru.kpfu.itis.kuzmin.skillshare.security.JwtTokenAuthentication;
import ru.kpfu.itis.kuzmin.skillshare.service.AuthService;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public TokenCoupleResponse login (@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest.username(), loginRequest.password());
    }

    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    TokenCoupleResponse refresh(@RequestBody TokenRequest refreshToken) {
        return authService.refresh(refreshToken);
    }


    @GetMapping("/api/v1/auth/check")
    @ResponseStatus(HttpStatus.OK)
    public void checkAuth() {}

    // проверка доступа на url-ы, для которых требуются роли, отличающиеся от USER
    @GetMapping("/check-role-access")
    @ResponseStatus(HttpStatus.OK)
    public Boolean accessRoleCheck(@RequestParam("url") String url) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() &&
                authentication instanceof JwtTokenAuthentication) {
            Stream<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority);
            if (url.equals("/create/article")) {
                List<String> resolved = List.of(Roles.AUTHOR.getFullName(), Roles.ADMIN.getFullName(), Roles.MODER.getFullName());
                return roles.anyMatch(resolved::contains);
            }
        }
        return false;
    }
}
