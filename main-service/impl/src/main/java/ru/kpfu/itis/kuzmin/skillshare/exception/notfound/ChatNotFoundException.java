package ru.kpfu.itis.kuzmin.skillshare.exception.notfound;

public class ChatNotFoundException extends NotFoundServiceException{

    public ChatNotFoundException(Long id) {
        super("Chat with id = %s - not found".formatted(id));
    }

}
