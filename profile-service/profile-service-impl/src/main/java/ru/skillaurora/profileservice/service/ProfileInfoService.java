package ru.skillaurora.profileservice.service;

import ru.skillaurora.profileservice.dto.request.AboutMeRequest;
import ru.skillaurora.profileservice.dto.request.BaseProfileRequest;
import ru.skillaurora.profileservice.dto.request.RatingUpdateRequest;
import ru.skillaurora.profileservice.dto.request.SkillsRequest;
import ru.skillaurora.profileservice.dto.response.BaseProfileInfoResponse;
import ru.skillaurora.profileservice.dto.response.ProfileInfoResponse;

import java.util.List;
import java.util.UUID;

public interface ProfileInfoService {

    ProfileInfoResponse getById(UUID id);

    void updateUsername(UUID id, String username);

    void updateRealName(UUID id, String name);

    void updateAboutMe(UUID id, AboutMeRequest aboutMe);

    void updateCountry(UUID id, String countryCode);

    void updateSkills(UUID id, SkillsRequest skills);

    void delete(UUID id);

    List<BaseProfileInfoResponse> getAllBaseInfoByIds(List<UUID> userIds);

    BaseProfileInfoResponse getBaseInfoById(UUID userId);

    void create(BaseProfileRequest baseProfile);

    BaseProfileInfoResponse getBaseInfoByUsername(String username);

    void updateRating(RatingUpdateRequest ratingUpdateRequest);
}
