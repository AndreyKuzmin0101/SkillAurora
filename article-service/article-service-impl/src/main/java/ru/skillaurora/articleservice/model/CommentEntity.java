package ru.skillaurora.articleservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Table(name = "comments")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentEntity {

    @Id
    @UuidGenerator
    private UUID id;
    @NotNull(message = "Необходимо указать автора.")
    private UUID authorId;
    @NotBlank(message = "Комментарий не может быть пустым.")
    @Size(max = 2000, message = "Комментарий не может быть длиннее 2000 символов.")
    private String content;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(fetch = FetchType.LAZY)
    private List<CommentEntity> children;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", referencedColumnName = "id")
    @NotNull(message = "Необходимо указать статью.")
    private ArticleEntity articleEntity;
}
