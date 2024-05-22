package ru.kpfu.itis.skillshare.mainservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "chats")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "first_user")
    private UserEntity firstUser;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "second_user")
    private UserEntity secondUser;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chat")
    private List<MessageEntity> messages;
}
