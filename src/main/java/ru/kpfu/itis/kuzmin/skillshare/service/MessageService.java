package ru.kpfu.itis.kuzmin.skillshare.service;

import ru.kpfu.itis.kuzmin.skillshare.dto.request.MessageRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.MessageResponseDto;

import java.util.List;

public interface MessageService {

    List<MessageResponseDto> getByChatId(Long chatId);

    void create(MessageRequestDto messageRequest, Long senderId);
}
