package ru.kpfu.itis.skillshare.mainservice.exception.notfound;

public class ChatNotFoundException extends NotFoundServiceException{

    public ChatNotFoundException(Long id) {
        super("Chat with id = %s - not found".formatted(id));
    }

}
