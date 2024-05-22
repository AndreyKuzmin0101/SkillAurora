package ru.kpfu.itis.skillshare.mainservice.exception.alreadyexitsts;

public class UserWithNameAlreadyExistsException extends AlreadyExistsServiceException {

    public UserWithNameAlreadyExistsException(String username) {
        super("User with username = %s - already exists".formatted(username));
    }
}
