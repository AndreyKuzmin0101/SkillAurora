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

    //TODO: шифорвание
    @Override
    public void update(UserEntity user) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        CriteriaUpdate<UserEntity> query = criteriaBuilder.createCriteriaUpdate(UserEntity.class);
        Root<UserEntity> root = query.from(UserEntity.class);

        query.set("username", user.getUsername());
        query.set("email", user.getEmail());
        query.set("password", user.getPassword());
        query.set("registerDate", user.getRegisterDate());
        query.set("rating", user.getRating());

        query.where(criteriaBuilder.equal(root.get("id"), user.getId()));

        em.createQuery(query).executeUpdate();
    }
}
