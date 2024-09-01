package ru.skillaurora.profileservice.exception.handler;


import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Обработчик возникающих ошибок.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> exceptions = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            exceptions.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(exceptions, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionMessage handlerBindException(HttpMessageNotReadableException exception) {
        return ExceptionMessage.builder()
                .exceptionName(exception.getClass().getSimpleName())
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> exceptions = new HashMap<>();
        ex.getConstraintViolations().forEach(cv -> {
            String fieldName = cv.getPropertyPath().toString();
            String errorMessage = cv.getMessage();
            exceptions.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(exceptions, HttpStatus.BAD_REQUEST);
    }

//    /**
//     * Формирует ответ на основе всех исключений {@link Exception}.
//     *
//     * @param exception исключение
//     * @return сформированный на основе исключения ответ
//     */
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public final ExceptionMessage onAllExceptions(Exception exception) {
//        return ExceptionMessage.builder()
//                .message(exception.getMessage())
//                .exceptionName(exception.getClass().getSimpleName())
//                .build();
//    }
}