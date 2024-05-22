package ru.kpfu.itis.kuzmin.skillshare.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.TokenRequest;
import ru.kpfu.itis.kuzmin.skillshare.security.JwtTokenAuthentication;
import ru.kpfu.itis.kuzmin.skillshare.security.exception.AuthenticationHeaderException;
import ru.kpfu.itis.kuzmin.skillshare.security.provider.JwtAccessTokenProvider;
import ru.kpfu.itis.kuzmin.skillshare.security.util.HttpHeaderUtil;
import ru.kpfu.itis.kuzmin.skillshare.security.util.SecurityConstants;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class ChatHandshakeInterceptor implements HandshakeInterceptor {

    private final JwtAccessTokenProvider jwtAccessTokenProvider;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws IOException {
        try {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpServletRequest httpServletRequest = servletRequest.getServletRequest();
            String token = httpServletRequest.getParameter("token");

            if (Objects.nonNull(token)) {
                Authentication authentication = authenticate(token);
                attributes.put("auth", authentication);
                return true;
            }
            throw new AuthenticationHeaderException("There is no access token");
        } catch (Exception exception) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            final Map<String, Object> body = new HashMap<>();
            body.put("status", 401);
            body.put("error", "Unauthorized");
            body.put("message", exception.getMessage());
            final ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getBody(), body);
            return false;
        }
    }

    private Authentication authenticate(String token) {
        try {
            Claims claims = jwtAccessTokenProvider.parseAccessToken(token);
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

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        // Ничего не делаем
    }
}
