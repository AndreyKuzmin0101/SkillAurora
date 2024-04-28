package ru.kpfu.itis.kuzmin.skillshare.repository.jpa.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.kuzmin.skillshare.model.UserEntity;
import ru.kpfu.itis.kuzmin.skillshare.repository.jpa.UserJpaRepository;

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
