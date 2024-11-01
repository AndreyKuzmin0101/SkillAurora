package ru.skillaurora.articleservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillaurora.articleservice.model.ArticleRatingEntity;

@Repository
public interface ArticleRatingRepository
        extends JpaRepository<ArticleRatingEntity, ArticleRatingEntity.ArticleRatingKey> {
}
