package ru.skillaurora.profileservice.dto.response;

import java.sql.Date;
import java.util.UUID;

public record ProfileInfoResponse(
        UUID userId,
        String username,
        String realName,
        String aboutMe,
        String profilePicture,
        String country,
        Long rating,
        Date registerDate,
        Statuses status
) {
}
