package ru.kpfu.itis.skillshare.mainservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.skillshare.mainservice.dto.RefreshTokenResponse;
import ru.kpfu.itis.skillshare.mainservice.exception.InvalidTokenException;
import ru.kpfu.itis.skillshare.mainservice.model.RefreshTokenEntity;
import ru.kpfu.itis.skillshare.mainservice.model.UserEntity;
import ru.kpfu.itis.skillshare.mainservice.repository.spring.RefreshTokenSpringRepository;
import ru.kpfu.itis.skillshare.mainservice.service.RefreshTokenService;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Value("${rt.expiration.access.millis}")
    private Long expirationAccessInMillis;

    private final RefreshTokenSpringRepository refreshTokenRepository;

    @Override
    public String generateRefreshToken(UserEntity user) {
        String token = UUID.randomUUID().toString();
        long currentTimeMillis = System.currentTimeMillis();
        refreshTokenRepository.deleteAllExpiredByUser(user.getId(), new Timestamp(currentTimeMillis));
        refreshTokenRepository.save(RefreshTokenEntity.builder()
                .refreshToken(token)
                .expiresAt(new Timestamp(currentTimeMillis + expirationAccessInMillis))
                .user(user)
                .build()
        );
        return token;
    }

    @Override
    public RefreshTokenResponse refresh(String refreshToken) {
        Optional<RefreshTokenEntity> optionalRefreshToken = refreshTokenRepository.findByRefreshToken(refreshToken);
        if (optionalRefreshToken.isPresent()) {
            RefreshTokenEntity tokenEntity = optionalRefreshToken.get();

            if (!tokenEntity.getUser().getEnabled()) {
                throw new InvalidTokenException("Invalid refresh token.");
            }

            long currentTimeMillis = System.currentTimeMillis();
            Timestamp current = new Timestamp(currentTimeMillis);
            if (tokenEntity.getExpiresAt().after(current)) {
                String token = UUID.randomUUID().toString();
                tokenEntity.setRefreshToken(token);
                tokenEntity.setExpiresAt(new Timestamp(currentTimeMillis + expirationAccessInMillis));
                refreshTokenRepository.save(tokenEntity);
                return new RefreshTokenResponse(tokenEntity.getUser(), token);
            }
            refreshTokenRepository.deleteAllExpiredByUser(tokenEntity.getUser().getId(), current);
            throw new InvalidTokenException("Token expired date error");
        }
        throw new InvalidTokenException("Invalid refresh token.");
    }
}
