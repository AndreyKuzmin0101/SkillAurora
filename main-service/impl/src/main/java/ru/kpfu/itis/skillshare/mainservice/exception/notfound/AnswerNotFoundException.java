package ru.kpfu.itis.skillshare.mainservice.exception.notfound;

public class AnswerNotFoundException extends NotFoundServiceException {

    public AnswerNotFoundException(Long id) {
        super("Answer with id = %s - not found".formatted(id));
    }

}
