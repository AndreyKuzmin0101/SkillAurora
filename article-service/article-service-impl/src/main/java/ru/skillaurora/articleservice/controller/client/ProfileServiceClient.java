package ru.skillaurora.articleservice.controller.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.skillaurora.articleservice.dto.response.BaseProfileInfo;

import java.util.UUID;

@FeignClient(name = "profile-service")
@RequestMapping("/internal/api/v2/users")
public interface ProfileServiceClient {

    @GetMapping("/{id}/profile/base")
    BaseProfileInfo getOneByUserId(@PathVariable("id") UUID userId);
}
