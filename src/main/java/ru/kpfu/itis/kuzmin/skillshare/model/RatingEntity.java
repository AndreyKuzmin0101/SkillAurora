package ru.kpfu.itis.kuzmin.skillshare.model;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "ratings")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RatingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "rating_value")
    private Integer ratingValue;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity user;
    @ManyToOne(fetch = FetchType.EAGER)
    private ArticleEntity article;
}
