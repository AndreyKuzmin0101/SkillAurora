package ru.kpfu.itis.kuzmin.skillshare.exception.alreadyexitsts;

public class UserWithNameAlreadyExistsException extends AlreadyExistsServiceException {

    public UserWithNameAlreadyExistsException(String username) {
        super("User with username = %s - already exists".formatted(username));
    }
}
