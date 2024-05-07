package ru.kpfu.itis.kuzmin.skillshare.repository.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.kuzmin.skillshare.model.RatingEntity;

import java.util.Optional;

@Repository
public interface RatingSpringRepository extends JpaRepository<RatingEntity, Long> {

    @Query("select coalesce(sum(r.ratingValue), 0) from RatingEntity r where r.article.id = :articleId")
    Long sumRatingByArticleId(@Param("articleId") Long articleId);

    @Query("select r from RatingEntity r where r.user.id = :userId and r.article.id = :articleId")
    Optional<RatingEntity> findByArticleAndUser(@Param("articleId") Long articleId, @Param("userId") Long userId);

}
