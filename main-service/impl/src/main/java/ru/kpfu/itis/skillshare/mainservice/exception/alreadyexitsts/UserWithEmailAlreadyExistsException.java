package ru.kpfu.itis.skillshare.mainservice.exception.alreadyexitsts;

public class UserWithEmailAlreadyExistsException extends AlreadyExistsServiceException {

    public UserWithEmailAlreadyExistsException(String email) {
        super("User with email = %s - already exists".formatted(email));
    }
}
