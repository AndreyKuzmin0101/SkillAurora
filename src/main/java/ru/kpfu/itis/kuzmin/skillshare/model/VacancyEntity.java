package ru.kpfu.itis.kuzmin.skillshare.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Table(name = "vacancies")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VacancyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "moderation_status")
    private String moderationStatus;

    @ManyToOne
    private UserEntity employer;
}
