package ru.kpfu.itis.kuzmin.skillshare.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.MessageRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.service.MessageService;

@RestController
@RequiredArgsConstructor
public class MessageController {


    private final MessageService messageService;

    //TODO: защитить очереди от чтения сторонними пользователями
    @MessageMapping("/chats")
    public void message(MessageRequestDto messageRequest) {
        messageService.create(messageRequest);
    }
}
