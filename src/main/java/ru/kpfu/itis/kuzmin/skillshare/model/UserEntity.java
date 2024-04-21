package ru.kpfu.itis.kuzmin.skillshare.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Table(name = "users")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 64)
    private String username;
    @Column(length = 128)
    private String email;
    @Column(length = 64)
    private String password;
    @Column(name = "register_date")
    private Date registerDate;
    private Integer rating;
}
