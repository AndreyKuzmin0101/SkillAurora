package ru.kpfu.itis.kuzmin.skillshare.exception.notfound;

import org.springframework.http.HttpStatus;
import ru.kpfu.itis.kuzmin.skillshare.exception.ServiceException;

public class NotFoundServiceException extends ServiceException {
    public NotFoundServiceException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
