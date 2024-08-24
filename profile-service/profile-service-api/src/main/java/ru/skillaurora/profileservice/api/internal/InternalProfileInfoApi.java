package ru.skillaurora.profileservice.api.internal;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.skillaurora.profileservice.dto.request.BaseProfileRequest;
import ru.skillaurora.profileservice.dto.response.BaseProfileInfoResponse;

import java.util.List;
import java.util.UUID;

@Hidden
@RequestMapping("/internal/api/v2/users")
public interface InternalProfileInfoApi {

    @GetMapping("/profile/base")
    @ResponseStatus(HttpStatus.OK)
    List<BaseProfileInfoResponse> getAllByUserIds(@RequestParam List<UUID> userIds);

    @GetMapping("/{id}/profile/base")
    @ResponseStatus(HttpStatus.OK)
    BaseProfileInfoResponse getOneByUserId(@PathVariable("id") UUID userId);

    @PostMapping("/profile")
    @ResponseStatus(HttpStatus.CREATED)
    void create(@Validated @RequestBody BaseProfileRequest baseProfile);
}
