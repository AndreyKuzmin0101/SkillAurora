package ru.kpfu.itis.skillshare.mainservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.skillshare.mainservice.dto.request.TokenRequest;
import ru.kpfu.itis.skillshare.mainservice.dto.RefreshTokenResponse;
import ru.kpfu.itis.skillshare.mainservice.dto.response.TokenCoupleResponse;
import ru.kpfu.itis.skillshare.mainservice.exception.BadCredentialsException;
import ru.kpfu.itis.skillshare.mainservice.model.UserEntity;
import ru.kpfu.itis.skillshare.mainservice.repository.spring.UserSpringRepository;
import ru.kpfu.itis.skillshare.mainservice.security.provider.JwtAccessTokenProvider;
import ru.kpfu.itis.skillshare.mainservice.service.AuthService;
import ru.kpfu.itis.skillshare.mainservice.service.RefreshTokenService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtAccessTokenProvider jwtAccessTokenProvider;
    private final RefreshTokenService refreshTokenService;

    private final UserSpringRepository userSpringRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public TokenCoupleResponse login(String username, String password) {
        Optional<UserEntity> optionalUser = userSpringRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();

            if (!user.getEnabled()) {
                throw new BadCredentialsException("Bad credentials");
            }

            if (passwordEncoder.matches(password, user.getPassword())) {
                String accessToken = jwtAccessTokenProvider.generateByUser(user);
                String refreshToken = refreshTokenService.generateRefreshToken(user);
                return new TokenCoupleResponse(accessToken, refreshToken);
            }
        }
        throw new BadCredentialsException("Bad credentials");
    }

    @Override
    public TokenCoupleResponse refresh(TokenRequest refreshToken) {
        RefreshTokenResponse newRefreshToken = refreshTokenService.refresh(refreshToken.token());
        String accessToken = jwtAccessTokenProvider.generateByUser(newRefreshToken.owner());
        return new TokenCoupleResponse(accessToken, newRefreshToken.token());
    }
}
