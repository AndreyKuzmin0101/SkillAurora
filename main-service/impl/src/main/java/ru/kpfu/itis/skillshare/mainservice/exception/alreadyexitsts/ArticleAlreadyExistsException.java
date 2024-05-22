package ru.kpfu.itis.skillshare.mainservice.exception.alreadyexitsts;

public class ArticleAlreadyExistsException extends AlreadyExistsServiceException {
    public ArticleAlreadyExistsException(String title) {
        super("Article with title = '%s' - already exists".formatted(title));
    }
}
