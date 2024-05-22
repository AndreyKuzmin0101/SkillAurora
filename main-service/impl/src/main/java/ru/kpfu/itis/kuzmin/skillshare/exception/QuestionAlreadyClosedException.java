package ru.kpfu.itis.kuzmin.skillshare.exception;

import org.springframework.http.HttpStatus;

public class QuestionAlreadyClosedException extends ServiceException {
    public QuestionAlreadyClosedException(Long id) {
        super("Question with id = %s - already closed", HttpStatus.BAD_REQUEST);
    }
}
