package ru.kpfu.itis.skillshare.mainservice.security.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.kpfu.itis.skillshare.mainservice.security.JwtTokenAuthentication;
import ru.kpfu.itis.skillshare.mainservice.security.exception.AuthenticationHeaderException;

public class SecurityUtil {

    // TODO: статус в случае ошибки
    public static Long getIdAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated() &&
                authentication instanceof JwtTokenAuthentication) {
            JwtTokenAuthentication jwtTokenAuthentication = (JwtTokenAuthentication) authentication;
            return jwtTokenAuthentication.getUserId();
        }

        throw new AuthenticationHeaderException("There is no authentication");
    }

    public static JwtTokenAuthentication getJwtTokenAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() &&
                authentication instanceof JwtTokenAuthentication) {
            return (JwtTokenAuthentication) authentication;
        }

        throw new AuthenticationHeaderException("There is no authentication");

    }
}
