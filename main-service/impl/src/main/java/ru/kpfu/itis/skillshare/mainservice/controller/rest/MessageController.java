package ru.kpfu.itis.skillshare.mainservice.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.skillshare.mainservice.api.MessageApi;
import ru.kpfu.itis.skillshare.mainservice.dto.request.MessageRequestDto;
import ru.kpfu.itis.skillshare.mainservice.dto.response.MessageResponseDto;
import ru.kpfu.itis.skillshare.mainservice.security.JwtTokenAuthentication;
import ru.kpfu.itis.skillshare.mainservice.service.MessageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MessageController implements MessageApi {

    private final MessageService messageService;

    @MessageMapping("/chats")
    public void message(MessageRequestDto messageRequest, @Header("auth") JwtTokenAuthentication authentication) {
        messageService.create(messageRequest, authentication.getUserId());
    }

    @Override
    public List<MessageResponseDto> getChatMessages(Long chatId) {
        return messageService.getByChatId(chatId);
    }
}
