package ru.kpfu.itis.kuzmin.skillshare.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Table(name = "questions")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 128)
    private String title;
    private String content;
    @Column(name = "created_date")
    private Date createdDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private QuestionStatus status;
    private Long views;
    @Transient
    private Integer countAnswers;

    @ManyToOne
    private UserEntity author;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "question_tag",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<TagEntity> tags;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
    private List<AnswerEntity> answers;
}
