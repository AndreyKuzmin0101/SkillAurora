package ru.kpfu.itis.kuzmin.skillshare.exception.notfound;

public class UserNotFoundException extends NotFoundServiceException {

    public UserNotFoundException(Long id) {
        super("User with id = %s - not found".formatted(id));
    }

    public UserNotFoundException(String email) {
        super("User with email = %s - not found".formatted(email));
    }
}
