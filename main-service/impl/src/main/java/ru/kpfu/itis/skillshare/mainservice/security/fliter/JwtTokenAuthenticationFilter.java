package ru.kpfu.itis.skillshare.mainservice.security.fliter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.kpfu.itis.skillshare.mainservice.dto.request.TokenRequest;
import ru.kpfu.itis.skillshare.mainservice.security.util.HttpResponseUtil;
import ru.kpfu.itis.skillshare.mainservice.security.util.SecurityConstants;
import ru.kpfu.itis.skillshare.mainservice.security.JwtTokenAuthentication;
import ru.kpfu.itis.skillshare.mainservice.security.exception.AuthenticationHeaderException;
import ru.kpfu.itis.skillshare.mainservice.security.provider.JwtAccessTokenProvider;
import ru.kpfu.itis.skillshare.mainservice.security.util.HttpHeaderUtil;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    private final JwtAccessTokenProvider jwtAccessTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            TokenRequest token = HttpHeaderUtil.getTokenFromAuthorizationHeader(request.getHeader(AUTHORIZATION));
            if (Objects.nonNull(token)) {
                Authentication authentication = authenticate(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            HttpResponseUtil.putExceptionInResponse(request, response,
                    exception, HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private Authentication authenticate(TokenRequest token) {
        try {
            Claims claims = jwtAccessTokenProvider.parseAccessToken(token.token());
            Long userId = Long.parseLong(claims.getSubject());
            List<String> roles = claims.get(SecurityConstants.ROLES, List.class);
            if (roles != null && !roles.isEmpty()) {
                List<SimpleGrantedAuthority> authorities = roles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .toList();
                return new JwtTokenAuthentication(userId, authorities);
            } else {
                throw new AuthenticationHeaderException("Invalid token");
            }
        } catch (ExpiredJwtException e) {
            throw new AuthenticationHeaderException("Token expired date error");
        } catch (JwtException | NumberFormatException e) {
            throw new AuthenticationHeaderException("Invalid token");
        }
    }
}
