package ru.kpfu.itis.skillshare.mainservice.service;

import ru.kpfu.itis.skillshare.mainservice.dto.request.MessageRequestDto;
import ru.kpfu.itis.skillshare.mainservice.dto.response.MessageResponseDto;

import java.util.List;

public interface MessageService {

    List<MessageResponseDto> getByChatId(Long chatId);

    void create(MessageRequestDto messageRequest, Long senderId);
}
