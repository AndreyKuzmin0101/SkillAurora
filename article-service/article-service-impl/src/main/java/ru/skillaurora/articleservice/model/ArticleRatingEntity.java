package ru.skillaurora.articleservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Table(name = "article_ratings")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArticleRatingEntity {

    @EmbeddedId
    private ArticleRatingKey compositeKey;
    @NotNull(message = "Не указано значение изменения рейтинга.")
    private Integer value;

    @Embeddable
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ArticleRatingKey implements Serializable {
        @ManyToOne
        @JoinColumn(name = "article_id")
        @NotNull(message = "Не указана статья.")
        private ArticleEntity article;
        @NotNull(message = "Не указан id пользователя.")
        private UUID userId;
    }
}
