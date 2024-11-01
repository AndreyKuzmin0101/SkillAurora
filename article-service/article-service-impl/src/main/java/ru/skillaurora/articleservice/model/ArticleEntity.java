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
import ru.skillaurora.articleservice.dto.response.BaseProfileInfo;
import ru.skillaurora.articleservice.dto.response.ModerStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Table(name = "articles")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArticleEntity {

    @Id
    @UuidGenerator
    private UUID id;
    @NotBlank(message = "Отсутствует название.")
    @Size(max = 128, message = "Название не может содержать более 128 символов.")
    private String title;
    @NotBlank(message = "Отсутствует содержание.")
    private String content;
    private String cover;
    @NotBlank(message = "Отсутствует описание.")
    @Size(max = 512, message = "Описание не может быть длиннее 512 символов.")
    private String description;
    @NotNull(message = "Отсутствует автор.")
    private UUID authorId;
    @Column(name = "publication_time", updatable = false)
    @CreationTimestamp
    private LocalDateTime publicationTime;
    @NotNull(message = "Отсутствует статус модерации.")
    private ModerStatus moderationStatus;
    @NotNull(message = "Не указано количество просмотров.")
    private Long views;
    @NotNull(message = "Не указан рейтинг")
    private Long rating;

    @OneToMany(fetch = FetchType.LAZY)
    private List<ArticleRatingEntity> ratings;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "article_tag",
            joinColumns = @JoinColumn(name = "article_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id")
    )
    private List<TagEntity> tags;

    @Transient
    private BaseProfileInfo author;
}
