package ru.skillaurora.profileservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.skillaurora.profileservice.api.ProfileInfoApi;
import ru.skillaurora.profileservice.dto.request.AboutMeRequest;
import ru.skillaurora.profileservice.dto.request.SkillsRequest;
import ru.skillaurora.profileservice.dto.response.ProfileInfoResponse;
import ru.skillaurora.profileservice.service.ProfileInfoService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ProfileInfoController implements ProfileInfoApi {

    private final ProfileInfoService profileInfoService;

    @Override
    public ProfileInfoResponse getByUserId(UUID id) {
        return profileInfoService.getById(id);
    }

    @Override
    public void updateUsername(UUID id, String username) {
        profileInfoService.updateUsername(id, username);
    }

    @Override
    public void updateRealName(UUID id, String name) {
        profileInfoService.updateRealName(id, name);
    }

    @Override
    public void updateAboutMe(UUID id, AboutMeRequest aboutMe) {
        profileInfoService.updateAboutMe(id, aboutMe);
    }

    @Override
    public void updateCountry(UUID id, String countryCode) {
        profileInfoService.updateCountry(id, countryCode);
    }

    @Override
    public void updateSkills(UUID id, SkillsRequest skills) {
        profileInfoService.updateSkills(id, skills);
    }

    @Override
    public void delete(UUID id) {
        profileInfoService.delete(id);
    }
}
