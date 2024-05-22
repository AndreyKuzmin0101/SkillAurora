package ru.kpfu.itis.kuzmin.skillshare.exception.alreadyexitsts;

public class TagAlreadyExistsException extends AlreadyExistsServiceException{
    public TagAlreadyExistsException(String name) {
        super("Tag with name = '%s' - already exists".formatted(name));
    }
}
