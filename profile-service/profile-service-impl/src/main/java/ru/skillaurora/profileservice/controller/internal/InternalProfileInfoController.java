package ru.skillaurora.profileservice.controller.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.skillaurora.profileservice.api.internal.InternalProfileInfoApi;
import ru.skillaurora.profileservice.dto.request.BaseProfileRequest;
import ru.skillaurora.profileservice.dto.response.BaseProfileInfoResponse;
import ru.skillaurora.profileservice.service.ProfileInfoService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class InternalProfileInfoController implements InternalProfileInfoApi {

    private final ProfileInfoService profileInfoService;

    @Override
    public List<BaseProfileInfoResponse> getAllByUserIds(List<UUID> userIds) {
        return profileInfoService.getAllBaseInfoByIds(userIds);
    }

    @Override
    public BaseProfileInfoResponse getOneByUserId(UUID userId) {
        return profileInfoService.getBaseInfoById(userId);
    }

    @Override
    public void create(BaseProfileRequest baseProfile) {
        profileInfoService.create(baseProfile);
    }

    @Override
    public BaseProfileInfoResponse getOneByUsername(String username) {
        return profileInfoService.getBaseInfoByUsername(username);
    }
}
