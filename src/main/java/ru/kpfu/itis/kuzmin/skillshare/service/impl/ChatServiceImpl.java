package ru.kpfu.itis.kuzmin.skillshare.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.ChatRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.ChatResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.exception.notfound.ChatNotFoundException;
import ru.kpfu.itis.kuzmin.skillshare.exception.notfound.UserNotFoundException;
import ru.kpfu.itis.kuzmin.skillshare.model.ChatEntity;
import ru.kpfu.itis.kuzmin.skillshare.model.UserEntity;
import ru.kpfu.itis.kuzmin.skillshare.repository.spring.ChatSpringRepository;
import ru.kpfu.itis.kuzmin.skillshare.repository.spring.UserSpringRepository;
import ru.kpfu.itis.kuzmin.skillshare.service.ChatService;
import ru.kpfu.itis.kuzmin.skillshare.utils.SecurityUtil;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final UserSpringRepository userSpringRepository;
    private final ChatSpringRepository chatSpringRepository;


    @Override
    public Long create(ChatRequestDto chatRequestDto) {
        UserEntity firstUser = SecurityUtil.getAuthenticatedUser();
        Optional<UserEntity> optionalSecondUser = userSpringRepository.findById(chatRequestDto.secondUserId());

        if (optionalSecondUser.isPresent()) {
            ChatEntity chat = chatSpringRepository.saveAndFlush(new ChatEntity(null, firstUser, optionalSecondUser.get(), null));
            return chat.getId();
        }

        throw new UserNotFoundException(chatRequestDto.secondUserId());
    }

    @Override
    public ChatResponseDto getById(Long chatId) {
//        UserEntity user = SecurityUtil.getAuthenticatedUser();
//        Optional<ChatEntity> optionalChat = chatSpringRepository.findById(chatId);
//
//        if (optionalChat.isPresent()) {
//            ChatEntity chat = optionalChat.get();
//            if (user.getId().equals(chat.getFirstUser().getId())) {
//                return chatMapper.toResponse(optionalChat.get());
//            } else if (user.getId().equals(chat.getSecondUser().getId())) {
//                UserEntity firstUser = chat.getFirstUser();
//                chat.setFirstUser(chat.getSecondUser());
//                chat.setSecondUser(firstUser);
//                return chatMapper.toResponse(optionalChat.get());
//            }
//        }
//        throw new ChatNotFoundException(chatId);
        return null;
    }
}
