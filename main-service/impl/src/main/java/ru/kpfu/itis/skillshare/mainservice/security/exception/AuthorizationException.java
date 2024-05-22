package ru.kpfu.itis.skillshare.mainservice.security.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthorizationException extends AuthenticationException {
    public AuthorizationException(String msg) {
        super(msg);
    }
}