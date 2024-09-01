package ru.skillaurora.profileservice.exception.model;

public class CountryNotFoundException extends NotFoundServiceException {

    public CountryNotFoundException(String code) {
        super("Страна с кодом = %s - не найдена.".formatted(code));
    }

}
