package ru.kpfu.itis.kuzmin.skillshare.service;

import ru.kpfu.itis.kuzmin.skillshare.dto.request.ChatRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.ChatResponseDto;

import java.util.List;

public interface ChatService {

    List<ChatResponseDto> getChatsAuthenticatedUser();

    Long create(ChatRequestDto chatRequestDto);

}
