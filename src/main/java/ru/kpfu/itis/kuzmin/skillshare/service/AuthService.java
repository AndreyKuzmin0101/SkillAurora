package ru.kpfu.itis.kuzmin.skillshare.service;


import ru.kpfu.itis.kuzmin.skillshare.dto.request.TokenRequest;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.TokenCoupleResponse;

public interface AuthService {
    TokenCoupleResponse login(String username, String password);

    TokenCoupleResponse refresh(TokenRequest refreshToken);
}
