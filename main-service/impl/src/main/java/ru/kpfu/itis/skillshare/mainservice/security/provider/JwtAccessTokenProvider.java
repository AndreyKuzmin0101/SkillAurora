package ru.kpfu.itis.skillshare.mainservice.security.provider;

import io.jsonwebtoken.Claims;
import ru.kpfu.itis.skillshare.mainservice.model.UserEntity;

import java.util.Map;

public interface JwtAccessTokenProvider {

    String generateByUser(UserEntity user);

    String generateAccessToken(String subject, Map<String, Object> data);

    Claims parseAccessToken(String accessToken);
}
