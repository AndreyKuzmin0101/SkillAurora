package ru.kpfu.itis.skillshare.mainservice.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.skillshare.mainservice.dto.request.LoginRequest;
import ru.kpfu.itis.skillshare.mainservice.dto.request.TokenRequest;
import ru.kpfu.itis.skillshare.mainservice.dto.response.TokenCoupleResponse;

@Tag(name = "Authentication | Аутентификация", description = "API аутентификации")
@RequestMapping("/api/v1/auth")
public interface AuthApi {

    @Operation(
            summary = "Войти в аккаунт",
            description = "Позволяет получить access и refresh токены"
    )
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    TokenCoupleResponse login (@RequestBody LoginRequest loginRequest);

    @Operation(
            summary = "Обновить токены",
            description = "Позволяет обновить access и refresh токены"
    )
    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    TokenCoupleResponse refresh(@RequestBody TokenRequest refreshToken);

    @Operation(
            summary = "Проверить аутентификацию",
            description = "Позволяет проверить аутентификацию по токену"
    )
    @SecurityRequirement(name = "JWT")
    @GetMapping("/check")
    @ResponseStatus(HttpStatus.OK)
    void checkAuth();

    // проверка доступа на url-ы, для которых требуются роли, отличающиеся от USER
    @Operation(
            summary = "Проверить доступ",
            description = "Позволяет проверить доступ к url, требующем специальной роли"
    )
    @GetMapping("/check-role-access")
    @ResponseStatus(HttpStatus.OK)
    Boolean accessRoleCheck(@RequestParam("url") String url);
}
