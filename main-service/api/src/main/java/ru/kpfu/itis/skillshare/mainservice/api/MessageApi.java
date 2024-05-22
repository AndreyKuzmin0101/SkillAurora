package ru.kpfu.itis.skillshare.mainservice.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kpfu.itis.skillshare.mainservice.dto.response.MessageResponseDto;

import java.util.List;

@Tag(name = "Messages | Сообщения", description = "API сообщений")
public interface MessageApi {

    @Operation(
            summary = "Получить сообщения чата",
            description = "Позволяет получить сообщения из чата по id, в котором состоит аутентифицированный пользователь"
    )
    @SecurityRequirement(name = "JWT")
    @GetMapping("/api/v1/chats/{chat-id}/messages")
    List<MessageResponseDto> getChatMessages(@PathVariable("chat-id") Long chatId);
}
