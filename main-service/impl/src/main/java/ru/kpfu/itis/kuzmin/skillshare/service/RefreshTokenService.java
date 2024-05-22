package ru.kpfu.itis.kuzmin.skillshare.service;

import ru.kpfu.itis.kuzmin.skillshare.dto.response.RefreshTokenResponse;
import ru.kpfu.itis.kuzmin.skillshare.model.UserEntity;

public interface RefreshTokenService {
    String generateRefreshToken(UserEntity user);

    RefreshTokenResponse refresh(String refreshToken);
}
