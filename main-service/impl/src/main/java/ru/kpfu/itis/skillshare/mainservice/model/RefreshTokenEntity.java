package ru.kpfu.itis.skillshare.mainservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Table(name = "refresh_tokens")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RefreshTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "refresh_token")
    private String refreshToken;
    @Column(name = "expires_at")
    private Timestamp expiresAt;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity user;
}
