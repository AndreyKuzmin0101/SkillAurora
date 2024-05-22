package ru.kpfu.itis.kuzmin.skillshare.security.provider;


import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.kuzmin.skillshare.model.RoleEntity;
import ru.kpfu.itis.kuzmin.skillshare.model.UserEntity;
import ru.kpfu.itis.kuzmin.skillshare.repository.spring.UserSpringRepository;
import ru.kpfu.itis.kuzmin.skillshare.security.util.SecurityConstants;

import java.time.Instant;
import java.util.*;


@Component
@RequiredArgsConstructor
public class JwtAccessTokenProviderImpl implements JwtAccessTokenProvider {


    @Value("${jwt.expiration.access.millis}")
    private Long expirationAccessInMillis;
    @Value("${jwt.secret}")
    private String jwtSecret;

    private final UserSpringRepository userSpringRepository;

    @Override
    public String generateByUser(UserEntity user) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(SecurityConstants.ROLES, user.getRoles().stream().map(RoleEntity::getName).toList());
        return generateAccessToken(user.getId().toString(), claims);
    }

    @Override
    public String generateAccessToken(String subject, Map<String, Object> data) {
        Map<String, Object> claims = new HashMap<>(data);
        claims.put(Claims.SUBJECT, subject);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(Date.from(Instant.now().plusMillis(expirationAccessInMillis)))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    @Override
    public Claims parseAccessToken(String accessToken) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(accessToken)
                .getBody();
    }
}
