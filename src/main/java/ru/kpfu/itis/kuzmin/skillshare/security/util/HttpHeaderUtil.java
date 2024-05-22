package ru.kpfu.itis.kuzmin.skillshare.security.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.TokenRequest;
import ru.kpfu.itis.kuzmin.skillshare.security.exception.AuthenticationHeaderException;

import java.util.Optional;


@UtilityClass
public class HttpHeaderUtil {

    public static TokenRequest getTokenFromAuthorizationHeader(String authorizationHeader) {
        if (authorizationHeader == null) {
            return null;
        }

        if (!authorizationHeader.startsWith(SecurityConstants.BEARER)) {
            throw new AuthenticationHeaderException("Invalid authentication scheme found in Authorization header");
        }


        String token = Optional.of(authorizationHeader)
                .filter(StringUtils::isNotBlank)
                .map(bearer -> StringUtils.removeStart(bearer, SecurityConstants.BEARER).trim())
                .filter(StringUtils::isNotBlank)
                .orElseThrow(() -> new AuthenticationHeaderException("Authorization header token is empty"));


        return new TokenRequest(token);
    }


}
