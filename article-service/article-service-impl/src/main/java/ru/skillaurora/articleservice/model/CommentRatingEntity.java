package ru.skillaurora.articleservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Table(name = "comment_ratings")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentRatingEntity {

    @EmbeddedId
    private CommentRatingKey compositeKey;
    @NotNull(message = "Не указано значение изменения рейтинга.")
    private Boolean value;

    @Embeddable
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CommentRatingKey implements Serializable {
        @ManyToOne
        @JoinColumn(name = "comment_id")
        @NotNull(message = "Не указан комментарий.")
        private CommentEntity comment;
        @NotNull(message = "Не указан id пользователя.")
        private UUID userId;
    }
}
