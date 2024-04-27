package ru.kpfu.itis.kuzmin.skillshare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.ChatRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.MessageResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.service.ChatService;
import ru.kpfu.itis.kuzmin.skillshare.service.MessageService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;
    private final MessageService messageService;

    @GetMapping("/chats")
    public String getAllChatsCurrentUser() {
        return null;
    }

    @GetMapping("/chats/{id}")
    public String getChatById(@PathVariable("id") Long chatId, Model model) {
        List<MessageResponseDto> messages = messageService.getByChatId(chatId);
        model.addAttribute("messages", messages);
        return "chat";
    }

    @PostMapping("/chats")
    public String createChat(ChatRequestDto chatRequest) {
        Long chatId = chatService.create(chatRequest);
        return "redirect:/chat/%s".formatted(chatId);
    }

}
