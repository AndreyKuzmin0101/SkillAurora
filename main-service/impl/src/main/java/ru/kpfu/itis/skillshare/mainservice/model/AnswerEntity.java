package ru.kpfu.itis.skillshare.mainservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

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
    private Date createdDate;
    @Column(name = "best_answer")
    private Boolean bestAnswer;

    @ManyToOne
    private UserEntity author;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionEntity question;
}
