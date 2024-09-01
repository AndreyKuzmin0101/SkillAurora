package ru.skillaurora.profileservice.exception.model;

import java.util.UUID;

public class ProfileNotFoundException extends NotFoundServiceException{

    public ProfileNotFoundException(UUID id) {
        super("Профиль пользователя с id = %s - не найден.".formatted(id));
    }

    public ProfileNotFoundException(String username) {
        super("Пользователь с именем = %s - не найден.".formatted(username));
    }
}
