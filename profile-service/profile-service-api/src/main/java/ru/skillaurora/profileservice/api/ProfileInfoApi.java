package ru.skillaurora.profileservice.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.skillaurora.profileservice.dto.request.AboutMeRequest;
import ru.skillaurora.profileservice.dto.request.SkillsRequest;
import ru.skillaurora.profileservice.dto.response.ProfileInfoResponse;

import java.util.UUID;

@Tag(name = "User Profile | Профиль Пользователя", description = "API профиля пользователя")
@Validated
@RequestMapping("/api/v2/users/{id}/profile")
public interface ProfileInfoApi {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ProfileInfoResponse getByUserId(@PathVariable("id") UUID id);

    @PatchMapping("/username")
    @ResponseStatus(HttpStatus.OK)
    void updateUsername(@PathVariable("id") UUID id,
                        @Size(min = 3, max = 32, message = "Username не должен короче 3 символов и превышать 32.")
                        @Pattern(regexp = "^[a-zA-Z0-9._-]+$", message = "Разрешено использовать латиницу, цифры и спец. символы: точка, подчёркивание и дефис")
                        @RequestParam("username")
                        String username);

    @PatchMapping("/real_name")
    @ResponseStatus(HttpStatus.OK)
    void updateRealName(@PathVariable("id") UUID id,
                        @Pattern(regexp = "^\\D*$", message = "Имя не должно содержать цифр.")
                        @Size(max = 32, message = "Имя не должно превышать 32 символа.")
                        @RequestParam("real_name")
                        String name);

    @PatchMapping("/about_me")
    @ResponseStatus(HttpStatus.OK)
    void updateAboutMe(@PathVariable("id") UUID id,
                       @Validated
                       @RequestBody
                       AboutMeRequest aboutMe);

    @PatchMapping("/country")
    @ResponseStatus(HttpStatus.OK)
    void updateCountry(@PathVariable("id") UUID id,
                       @Size(min = 2, max = 2, message = "Код состоит из двух символов.")
                       @RequestParam("code")
                       String countryCode);

    @PutMapping("/skills")
    @ResponseStatus(HttpStatus.OK)
    void updateSkills(@PathVariable("id") UUID id, @RequestBody SkillsRequest skills);

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("id") UUID id);
}
