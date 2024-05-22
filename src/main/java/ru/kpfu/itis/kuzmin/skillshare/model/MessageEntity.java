package ru.kpfu.itis.kuzmin.skillshare.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Table(name = "messages")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Column(name = "send_time")
    private Timestamp sendTime;

    @ManyToOne
    private ChatEntity chat;

    @ManyToOne
    private UserEntity sender;
}
