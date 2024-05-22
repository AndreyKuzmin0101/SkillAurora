package ru.kpfu.itis.skillshare.mainservice.service;


import ru.kpfu.itis.skillshare.mainservice.dto.request.TokenRequest;
import ru.kpfu.itis.skillshare.mainservice.dto.response.TokenCoupleResponse;

public interface AuthService {
    TokenCoupleResponse login(String username, String password);

    TokenCoupleResponse refresh(TokenRequest refreshToken);
}
