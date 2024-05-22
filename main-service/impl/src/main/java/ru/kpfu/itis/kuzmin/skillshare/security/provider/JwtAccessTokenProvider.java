package ru.kpfu.itis.kuzmin.skillshare.security.provider;

import io.jsonwebtoken.Claims;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.TokenRequest;
import ru.kpfu.itis.kuzmin.skillshare.model.UserEntity;

import java.util.Date;
import java.util.Map;

public interface JwtAccessTokenProvider {

    String generateByUser(UserEntity user);

    String generateAccessToken(String subject, Map<String, Object> data);

    Claims parseAccessToken(String accessToken);
}
