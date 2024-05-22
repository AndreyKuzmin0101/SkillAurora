package ru.kpfu.itis.kuzmin.skillshare.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.MessageRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.MessageResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.exception.notfound.ChatNotFoundException;
import ru.kpfu.itis.kuzmin.skillshare.mapper.MessageMapper;
import ru.kpfu.itis.kuzmin.skillshare.model.ChatEntity;
import ru.kpfu.itis.kuzmin.skillshare.model.MessageEntity;
import ru.kpfu.itis.kuzmin.skillshare.model.UserEntity;
import ru.kpfu.itis.kuzmin.skillshare.repository.spring.ChatSpringRepository;
import ru.kpfu.itis.kuzmin.skillshare.repository.spring.MessageSpringRepository;
import ru.kpfu.itis.kuzmin.skillshare.repository.spring.UserSpringRepository;
import ru.kpfu.itis.kuzmin.skillshare.security.exception.AuthorizationException;
import ru.kpfu.itis.kuzmin.skillshare.security.util.SecurityUtil;
import ru.kpfu.itis.kuzmin.skillshare.service.ChatService;
import ru.kpfu.itis.kuzmin.skillshare.service.MessageService;
import ru.kpfu.itis.kuzmin.skillshare.utils.UserProfileUtil;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageMapper messageMapper;

    private final MessageSpringRepository messageSpringRepository;
    private final ChatSpringRepository chatSpringRepository;
    private final UserSpringRepository userSpringRepository;

    //TODO: вынести проверку доступа в Spring Security
    @Override
    public List<MessageResponseDto> getByChatId(Long chatId) {
        Long userId = SecurityUtil.getIdAuthenticatedUser();

        Optional<ChatEntity> optionalChat = chatSpringRepository.findById(chatId);

        if (optionalChat.isPresent()) {
            ChatEntity chat = optionalChat.get();
            if (chat.getFirstUser().getId().equals(userId) ||
                    chat.getSecondUser().getId().equals(userId)) {
                return messageSpringRepository.findAllByChatId(chatId).stream()
                        .peek(messageEntity ->
                                messageEntity.setSender(
                                        UserProfileUtil.processUser(messageEntity.getSender())))
                        .map(messageMapper::toResponse)
                        .toList();
            }
            throw new AuthorizationException("An access error has occurred");
        }
        throw new ChatNotFoundException(chatId);
    }

    @Override
    public void create(MessageRequestDto messageRequest, Long senderId) {
        Optional<ChatEntity> chatOptional = chatSpringRepository.findById(messageRequest.chatId());

        if (chatOptional.isPresent()) {
            ChatEntity chat = chatOptional.get();
            UserEntity sender = userSpringRepository.findById(senderId).get();

            if (chat.getFirstUser().getId().equals(sender.getId()) ||
                    chat.getSecondUser().getId().equals(sender.getId())) {
                MessageEntity message = MessageEntity.builder()
                        .sender(UserProfileUtil.processUser(sender))
                        .chat(chat)
                        .content(messageRequest.content())
                        .sendTime(new Timestamp(System.currentTimeMillis()))
                        .build();

                messageSpringRepository.saveAndFlush(message);

                messagingTemplate.convertAndSendToUser(
                        chat.getId().toString(), "/queue/messages",
                        messageMapper.toResponse(message)
                );
            } else {
                throw new AuthorizationException("An access error has occurred");
            }
        } else {
            throw new ChatNotFoundException(messageRequest.chatId());
        }
    }
}
