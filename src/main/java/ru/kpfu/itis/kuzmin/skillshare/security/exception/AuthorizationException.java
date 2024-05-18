package ru.kpfu.itis.kuzmin.skillshare.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AuthorizationException extends AuthenticationException {
    public AuthorizationException(String msg) {
        super(msg);
    }
}