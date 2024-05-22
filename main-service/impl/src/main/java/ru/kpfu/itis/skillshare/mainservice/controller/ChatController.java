package ru.kpfu.itis.skillshare.mainservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.skillshare.mainservice.dto.request.ChatRequestDto;
import ru.kpfu.itis.skillshare.mainservice.dto.response.ChatResponseDto;
import ru.kpfu.itis.skillshare.mainservice.service.ChatService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/chats")
    public String getChatsView() {
        return "chats";
    }

    @GetMapping("/api/v1/chats/me")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<ChatResponseDto> getAllChatsCurrentUser() {
        return chatService.getChatsAuthenticatedUser();
    }


    @PostMapping("/api/v1/chats")
    public String createChat(ChatRequestDto chatRequest) {
        Long chatId = chatService.create(chatRequest);
        return "redirect:/chats/%s".formatted(chatId);
    }

}
