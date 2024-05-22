package ru.kpfu.itis.kuzmin.skillshare.exception.alreadyexitsts;

import org.springframework.http.HttpStatus;
import ru.kpfu.itis.kuzmin.skillshare.exception.ServiceException;

public class AlreadyExistsServiceException extends ServiceException
{
    public AlreadyExistsServiceException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
