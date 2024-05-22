package ru.kpfu.itis.kuzmin.skillshare.security.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthenticationHeaderException extends AuthenticationException {

    public AuthenticationHeaderException(String msg) {
        super(msg);
    }
}
