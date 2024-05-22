package ru.kpfu.itis.kuzmin.skillshare.repository.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.kuzmin.skillshare.model.UserEntity;

import java.util.Optional;

@Repository
public interface UserSpringRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);

    @Modifying
    @Query("update UserEntity u set u.profileImage = :url where u.id = :id")
    void updateProfileImage(@Param("id") Long userId, @Param("url") String url);

}
