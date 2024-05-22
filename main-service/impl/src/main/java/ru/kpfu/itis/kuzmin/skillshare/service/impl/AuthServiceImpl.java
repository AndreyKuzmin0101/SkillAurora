package ru.kpfu.itis.kuzmin.skillshare.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.TokenRequest;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.RefreshTokenResponse;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.TokenCoupleResponse;
import ru.kpfu.itis.kuzmin.skillshare.exception.BadCredentialsException;
import ru.kpfu.itis.kuzmin.skillshare.model.UserEntity;
import ru.kpfu.itis.kuzmin.skillshare.repository.spring.UserSpringRepository;
import ru.kpfu.itis.kuzmin.skillshare.security.provider.JwtAccessTokenProvider;
import ru.kpfu.itis.kuzmin.skillshare.service.AuthService;
import ru.kpfu.itis.kuzmin.skillshare.service.RefreshTokenService;

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
