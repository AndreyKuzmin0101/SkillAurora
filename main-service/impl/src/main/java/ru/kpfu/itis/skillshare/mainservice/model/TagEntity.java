package ru.kpfu.itis.skillshare.mainservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Table(name = "tags")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 64)
    private String name;
    private String description;
    @Column(name = "is_custom")
    private Boolean custom;
}
