package ru.kpfu.itis.kuzmin.skillshare.exception.notfound;

public class ArticleNotFoundException extends NotFoundServiceException{
    public ArticleNotFoundException(Long id) {
        super("Article with id = %s - not found".formatted(id));
    }

}
