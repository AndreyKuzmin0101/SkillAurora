package ru.kpfu.itis.kuzmin.skillshare.model;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Table(name = "users")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 64)
    private String username;
    @Column(name = "real_name")
    private String realName;
    private Integer age;
    @Column(length = 128)
    private String email;
    private String country;
    private String city;
    @Column(length = 64)
    private String password;
    @Column(name = "register_date")
    private Date registerDate;
    private Integer rating;
    private String profileImage;
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_tag",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id")
    )
    private List<TagEntity> skills;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<RoleEntity> roles;
}
