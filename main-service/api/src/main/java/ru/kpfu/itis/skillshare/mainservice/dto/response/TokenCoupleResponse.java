package ru.kpfu.itis.skillshare.mainservice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель для получения пары access / refresh токенов")
public record TokenCoupleResponse(String accessToken, String refreshToken) {

}
