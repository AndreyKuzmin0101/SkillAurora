package ru.skillaurora.profileservice.dto.response;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public record ProfileInfoResponse(
        UUID userId,
        String username,
        String realName,
        String aboutMe,
        String profilePicture,
        CountryResponse country,
        Long rating,
        Date registerDate,
        AccountStatus status,
        List<SkillResponse> skills
) {
}
