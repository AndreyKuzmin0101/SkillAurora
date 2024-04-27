package ru.kpfu.itis.kuzmin.skillshare.service;

import ru.kpfu.itis.kuzmin.skillshare.dto.request.ChatRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.ChatResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.MessageResponseDto;

import java.util.List;

public interface ChatService {

    Long create(ChatRequestDto chatRequestDto);

    ChatResponseDto getById(Long chatId);
}
