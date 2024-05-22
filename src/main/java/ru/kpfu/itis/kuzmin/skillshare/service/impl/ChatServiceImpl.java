package ru.kpfu.itis.kuzmin.skillshare.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.ChatRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.ChatResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.exception.notfound.UserNotFoundException;
import ru.kpfu.itis.kuzmin.skillshare.mapper.UserMapper;
import ru.kpfu.itis.kuzmin.skillshare.model.ChatEntity;
import ru.kpfu.itis.kuzmin.skillshare.model.UserEntity;
import ru.kpfu.itis.kuzmin.skillshare.repository.spring.ChatSpringRepository;
import ru.kpfu.itis.kuzmin.skillshare.repository.spring.UserSpringRepository;
import ru.kpfu.itis.kuzmin.skillshare.service.ChatService;
import ru.kpfu.itis.kuzmin.skillshare.security.util.SecurityUtil;
import ru.kpfu.itis.kuzmin.skillshare.utils.UserProfileUtil;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final UserSpringRepository userSpringRepository;
    private final ChatSpringRepository chatSpringRepository;

    private final UserMapper userMapper;


    @Override
    public List<ChatResponseDto> getChatsAuthenticatedUser() {
        Long userId = SecurityUtil.getIdAuthenticatedUser();

        List<ChatEntity> chats = chatSpringRepository.findByUserId(userId);
        for (ChatEntity chat: chats) {
            if (chat.getSecondUser().getId().equals(userId)) {
                chat.setSecondUser(chat.getFirstUser());
            }
            chat.setSecondUser(UserProfileUtil.processUser(chat.getSecondUser()));
        }

        return chats.stream()
                .map(chatEntity -> ChatResponseDto.builder()
                        .id(chatEntity.getId())
                        .secondUser(
                                userMapper.toResponse(chatEntity.getSecondUser())
                        )
                        .build()
                ).toList();
    }

    @Override
    public Long create(ChatRequestDto chatRequestDto) {
        UserEntity firstUser = UserEntity.builder()
                .id(SecurityUtil.getIdAuthenticatedUser())
                .build();
        Optional<UserEntity> optionalSecondUser = userSpringRepository.findById(chatRequestDto.secondUserId());

        if (optionalSecondUser.isPresent()) {
            UserEntity secondUser = optionalSecondUser.get();
            ChatEntity chat = chatSpringRepository.saveAndFlush(ChatEntity.builder()
                    .firstUser(firstUser)
                    .secondUser(secondUser)
                    .build());
            return chat.getId();
        }

        throw new UserNotFoundException(chatRequestDto.secondUserId());
    }
}
