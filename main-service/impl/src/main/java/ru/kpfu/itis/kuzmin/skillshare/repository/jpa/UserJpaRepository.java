package ru.kpfu.itis.kuzmin.skillshare.repository.jpa;

import ru.kpfu.itis.kuzmin.skillshare.model.UserEntity;

public interface UserJpaRepository {
    void update(UserEntity user);
}
