package ru.kpfu.itis.kuzmin.skillshare.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.kpfu.itis.kuzmin.skillshare.model.UserEntity;
import ru.kpfu.itis.kuzmin.skillshare.security.BaseUserDetails;

public class SecurityUtil {

    // TODO: статус в случае ошибки
    public static UserEntity getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof BaseUserDetails) {
                UserEntity user = ((BaseUserDetails) principal).getUser();
                if (user != null) {
                    return user;
                }
            }
        }

        throw new RuntimeException("There is no authentication");
    }
}
