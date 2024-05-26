package ru.kpfu.itis.skillshare.mainservice.exception.alreadyexitsts;


public class ChatAlreadyExistsException extends AlreadyExistsServiceException {
    public ChatAlreadyExistsException(Long firstUserId, Long secondUserId) {
        super("A chat between user %s and %s - already exists".formatted(firstUserId, secondUserId));
    }
}
