package ru.kpfu.itis.skillshare.mainservice.exception.notfound;

public class ArticleNotFoundException extends NotFoundServiceException{

    public ArticleNotFoundException() {
        super("Article not found, ops!");
    }

    public ArticleNotFoundException(Long id) {
        super("Article with id = %s - not found".formatted(id));
    }

}
