package ru.kpfu.itis.kuzmin.skillshare.repository.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kpfu.itis.kuzmin.skillshare.model.RefreshTokenEntity;

import java.sql.Timestamp;
import java.util.Optional;

public interface RefreshTokenSpringRepository extends JpaRepository<RefreshTokenEntity, Long> {

    Optional<RefreshTokenEntity> findByRefreshToken(String refreshToken);

    @Modifying
    @Query("delete from RefreshTokenEntity rt where rt.user.id = :userId and rt.expiresAt >= :current")
    void deleteAllExpiredByUser(@Param("userId") Long userId, @Param("current") Timestamp current);

}
