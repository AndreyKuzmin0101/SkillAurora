package ru.skillaurora.profileservice.api.internal;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.skillaurora.profileservice.dto.response.BaseProfileInfoResponse;

import java.util.List;
import java.util.UUID;

@Hidden
@RequestMapping("/internal/api/v2/users")
public interface InternalProfileInfoApi {

    @GetMapping("/profile/base")
    List<BaseProfileInfoResponse> getAllByUserIds(@RequestParam List<UUID> userIds);

    @GetMapping("/{id}/profile/base")
    BaseProfileInfoResponse getOneByUserId(@PathVariable("id") UUID userId);
}
