package ru.kpfu.itis.skillshare.mainservice.exception.alreadyexitsts;

public class TagAlreadyExistsException extends AlreadyExistsServiceException{
    public TagAlreadyExistsException(String name) {
        super("Tag with name = '%s' - already exists".formatted(name));
    }
}
