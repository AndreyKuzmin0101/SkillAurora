package ru.kpfu.itis.kuzmin.skillshare.exception.alreadyexitsts;

import org.springframework.http.HttpStatus;
import ru.kpfu.itis.kuzmin.skillshare.exception.ServiceException;

public class UserAlreadyExistsException extends AlreadyExistsServiceException {

    public UserAlreadyExistsException(String email) {
        super("User with email = %s - already exists".formatted(email));
    }
}
