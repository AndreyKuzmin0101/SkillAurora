package ru.kpfu.itis.kuzmin.skillshare.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "answers")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @Column(name = "created_date")
    private String createdDate;

    @ManyToOne
    private UserEntity author;
    @ManyToOne
    private QuestionEntity question;
}
