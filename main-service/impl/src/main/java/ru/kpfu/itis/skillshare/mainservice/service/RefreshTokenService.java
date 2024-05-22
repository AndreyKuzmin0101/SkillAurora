package ru.kpfu.itis.skillshare.mainservice.service;

import ru.kpfu.itis.skillshare.mainservice.dto.RefreshTokenResponse;
import ru.kpfu.itis.skillshare.mainservice.model.UserEntity;

public interface RefreshTokenService {
    String generateRefreshToken(UserEntity user);

    RefreshTokenResponse refresh(String refreshToken);
}
