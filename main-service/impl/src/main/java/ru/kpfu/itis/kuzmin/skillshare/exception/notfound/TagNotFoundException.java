package ru.kpfu.itis.kuzmin.skillshare.exception.notfound;

public class TagNotFoundException extends NotFoundServiceException{
    public TagNotFoundException(String name) {
        super("Tag with name = %s - not found".formatted(name));
    }

}

