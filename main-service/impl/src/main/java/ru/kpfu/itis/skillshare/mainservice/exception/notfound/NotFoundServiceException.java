package ru.kpfu.itis.skillshare.mainservice.exception.notfound;

import org.springframework.http.HttpStatus;
import ru.kpfu.itis.skillshare.mainservice.exception.ServiceException;

public class NotFoundServiceException extends ServiceException {
    public NotFoundServiceException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
