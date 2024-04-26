package ru.kpfu.itis.kuzmin.skillshare.repository.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.kuzmin.skillshare.model.ArticleEntity;
import ru.kpfu.itis.kuzmin.skillshare.model.UserEntity;

import java.util.Optional;

@Repository
public interface ArticleSpringRepository extends JpaRepository<ArticleEntity, Long> {

    Optional<ArticleEntity> findByTitle(String title);

    @Query("select a.author from ArticleEntity a where a.id = :articleId")
    Optional<UserEntity> findAuthorByArticleId(@Param("articleId") Long articleId);
}
