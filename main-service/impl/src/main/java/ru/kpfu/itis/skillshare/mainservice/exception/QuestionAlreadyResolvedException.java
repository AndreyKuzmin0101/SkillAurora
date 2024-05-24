package ru.kpfu.itis.skillshare.mainservice.exception;

import org.springframework.http.HttpStatus;

public class QuestionAlreadyResolvedException extends ServiceException {
    public QuestionAlreadyResolvedException(Long id) {
        super("Question with id = %s - already resolved", HttpStatus.BAD_REQUEST);
    }
}