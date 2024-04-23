package ru.kpfu.itis.kuzmin.skillshare.exception.alreadyexitsts;

public class UserWithEmailAlreadyExistsException extends AlreadyExistsServiceException {

    public UserWithEmailAlreadyExistsException(String email) {
        super("User with email = %s - already exists".formatted(email));
    }
}
