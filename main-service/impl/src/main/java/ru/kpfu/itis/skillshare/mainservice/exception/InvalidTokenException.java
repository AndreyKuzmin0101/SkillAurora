package ru.kpfu.itis.skillshare.mainservice.exception;

import org.springframework.http.HttpStatus;

public class InvalidTokenException extends ServiceException {
    public InvalidTokenException(String msg) {
        super(msg, HttpStatus.FORBIDDEN);
    }
}
