package ru.kpfu.itis.skillshare.mainservice.repository.jpa.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.skillshare.mainservice.model.UserEntity;
import ru.kpfu.itis.skillshare.mainservice.repository.jpa.UserJpaRepository;

@Repository
public class UserJpaRepositoryImpl implements UserJpaRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void update(UserEntity user) {
        em.merge(user);
        em.flush();
    }
}
