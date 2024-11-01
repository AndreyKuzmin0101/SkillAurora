package ru.skillaurora.articleservice.exception.model;

import java.util.UUID;

public class ArticleNotFoundException extends NotFoundServiceException{

    public ArticleNotFoundException(UUID id) {
        super("Статья с id = %s - не найдена.".formatted(id));
    }
}