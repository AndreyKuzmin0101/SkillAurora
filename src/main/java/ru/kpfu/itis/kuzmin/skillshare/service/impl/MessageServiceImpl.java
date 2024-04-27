package ru.kpfu.itis.kuzmin.skillshare.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.MessageRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.MessageResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.UserResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.model.ChatEntity;
import ru.kpfu.itis.kuzmin.skillshare.model.MessageEntity;
import ru.kpfu.itis.kuzmin.skillshare.model.UserEntity;
import ru.kpfu.itis.kuzmin.skillshare.repository.spring.MessageSpringRepository;
import ru.kpfu.itis.kuzmin.skillshare.repository.spring.UserSpringRepository;
import ru.kpfu.itis.kuzmin.skillshare.service.ChatService;
import ru.kpfu.itis.kuzmin.skillshare.service.MessageService;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final SimpMessagingTemplate messagingTemplate;

    private final ChatService chatService;

    private final MessageSpringRepository messageSpringRepository;
    private final UserSpringRepository userSpringRepository;

    @Override
    public List<MessageResponseDto> getByChatId(Long chatId) {
//        return messageSpringRepository.findAllByChatId(chatId).stream()
//                .peek(messageEntity -> messageEntity.getSender().setEmail(null))
//                .map(messageEntity -> MessageResponseDto.builder()
//                        .content(messageEntity.getContent())
//                        .sendTime(messageEntity.getSendTime())
//                        .sender(UserResponseDto.builder()
//                                .username(messageEntity.getSender().getUsername())
//                                .profileImage(messageEntity.getSender().getProfileImage())
//                                .build()
//                        )
//                        .build())
//                .toList();
        return null;
    }

    @Override
    public void create(MessageRequestDto messageRequest) {
//        MessageEntity message = messageSpringRepository.saveAndFlush();
        messagingTemplate.convertAndSendToUser(
                messageRequest.chatId().toString(), "/queue/messages",
                MessageResponseDto.builder()
                        .content(messageRequest.content())
                        .sendTime(new Timestamp(System.currentTimeMillis()))
                        .sender(messageRequest.sender())
                        .build()
        );
    }
}
