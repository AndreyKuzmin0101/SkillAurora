package ru.kpfu.itis.kuzmin.skillshare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.ChatRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.ChatResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.MessageResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.service.ChatService;
import ru.kpfu.itis.kuzmin.skillshare.service.MessageService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/chats")
    public String getChatsView() {
        return "chats";
    }

    @GetMapping("/api/v1/chats")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<ChatResponseDto> getAllChatsCurrentUser() {
        return chatService.getChatsAuthenticatedUser();
    }


    @PostMapping("/chats")
    public String createChat(ChatRequestDto chatRequest) {
        Long chatId = chatService.create(chatRequest);
        return "redirect:/chats/%s".formatted(chatId);
    }

}
