package ru.kpfu.itis.skillshare.mainservice.repository.jpa;

import ru.kpfu.itis.skillshare.mainservice.model.UserEntity;

public interface UserJpaRepository {
    void update(UserEntity user);
}
