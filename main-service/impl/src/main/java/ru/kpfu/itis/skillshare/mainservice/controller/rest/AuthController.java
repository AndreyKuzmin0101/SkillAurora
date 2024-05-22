package ru.kpfu.itis.skillshare.mainservice.controller.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.skillshare.mainservice.api.AuthApi;
import ru.kpfu.itis.skillshare.mainservice.dto.Roles;
import ru.kpfu.itis.skillshare.mainservice.dto.request.LoginRequest;
import ru.kpfu.itis.skillshare.mainservice.dto.request.TokenRequest;
import ru.kpfu.itis.skillshare.mainservice.dto.response.TokenCoupleResponse;
import ru.kpfu.itis.skillshare.mainservice.security.JwtTokenAuthentication;
import ru.kpfu.itis.skillshare.mainservice.service.AuthService;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final AuthService authService;

    @Override
    public TokenCoupleResponse login (@Validated LoginRequest loginRequest) {
        return authService.login(loginRequest.username(), loginRequest.password());
    }

    @Override
    public TokenCoupleResponse refresh(@Validated TokenRequest refreshToken) {
        return authService.refresh(refreshToken);
    }

    @Override
    public void checkAuth() {}

    // проверка доступа на url-ы, для которых требуются роли, отличающиеся от USER
    @Override
    public Boolean accessRoleCheck(String url) {
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
