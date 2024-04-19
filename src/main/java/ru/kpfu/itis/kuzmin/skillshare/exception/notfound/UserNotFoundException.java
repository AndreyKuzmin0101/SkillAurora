package ru.kpfu.itis.kuzmin.skillshare.exception.notfound;

public class UserNotFoundException extends NotFoundServiceException {

    public UserNotFoundException(String email) {
        super("User with email = %s - not found".formatted(email));
    }
}
