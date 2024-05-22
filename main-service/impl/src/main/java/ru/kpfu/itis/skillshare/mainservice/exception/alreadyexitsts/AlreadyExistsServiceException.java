package ru.kpfu.itis.skillshare.mainservice.exception.alreadyexitsts;

import org.springframework.http.HttpStatus;
import ru.kpfu.itis.skillshare.mainservice.exception.ServiceException;

public class AlreadyExistsServiceException extends ServiceException
{
    public AlreadyExistsServiceException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
