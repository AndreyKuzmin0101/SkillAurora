package ru.kpfu.itis.kuzmin.skillshare.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.MessageRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.MessageResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.security.JwtTokenAuthentication;
import ru.kpfu.itis.kuzmin.skillshare.service.MessageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MessageController {


    private final MessageService messageService;

    @MessageMapping("/chats")
    public void message(MessageRequestDto messageRequest, @Header("auth") JwtTokenAuthentication authentication) {
        messageService.create(messageRequest, authentication.getUserId());
    }

    @GetMapping("/api/v1/messages/{chat-id}")
    public List<MessageResponseDto> getChatMessages(@PathVariable("chat-id") Long chatId) {
        return messageService.getByChatId(chatId);
    }
}
