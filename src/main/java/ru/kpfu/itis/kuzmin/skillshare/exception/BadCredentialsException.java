package ru.kpfu.itis.kuzmin.skillshare.exception;

import org.springframework.http.HttpStatus;

public class BadCredentialsException extends ServiceException {
    public BadCredentialsException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
