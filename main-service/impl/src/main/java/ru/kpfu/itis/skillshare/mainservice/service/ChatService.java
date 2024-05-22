package ru.kpfu.itis.skillshare.mainservice.service;

import ru.kpfu.itis.skillshare.mainservice.dto.request.ChatRequestDto;
import ru.kpfu.itis.skillshare.mainservice.dto.response.ChatResponseDto;

import java.util.List;

public interface ChatService {

    List<ChatResponseDto> getChatsAuthenticatedUser();

    Long create(ChatRequestDto chatRequestDto);

}
