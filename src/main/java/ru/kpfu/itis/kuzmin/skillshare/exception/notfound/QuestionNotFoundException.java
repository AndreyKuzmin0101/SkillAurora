package ru.kpfu.itis.kuzmin.skillshare.exception.notfound;

public class QuestionNotFoundException extends NotFoundServiceException {

    public QuestionNotFoundException(Long id) {
        super("Question with id = %s - not found".formatted(id));
    }

}
