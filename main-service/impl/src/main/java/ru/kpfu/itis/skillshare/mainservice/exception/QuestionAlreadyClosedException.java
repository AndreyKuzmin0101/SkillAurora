package ru.kpfu.itis.skillshare.mainservice.exception;

import org.springframework.http.HttpStatus;

public class QuestionAlreadyClosedException extends ServiceException {
    public QuestionAlreadyClosedException(Long id) {
        super("Question with id = %s - already closed".formatted(id), HttpStatus.BAD_REQUEST);
    }
}
