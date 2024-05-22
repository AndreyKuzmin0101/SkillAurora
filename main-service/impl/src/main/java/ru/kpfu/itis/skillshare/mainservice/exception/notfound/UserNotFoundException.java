package ru.kpfu.itis.skillshare.mainservice.exception.notfound;

public class UserNotFoundException extends NotFoundServiceException {

    public UserNotFoundException(Long id) {
        super("User with id = %s - not found".formatted(id));
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }
}
