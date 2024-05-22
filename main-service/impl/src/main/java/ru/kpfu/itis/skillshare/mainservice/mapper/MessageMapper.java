package ru.kpfu.itis.skillshare.mainservice.mapper;

import org.mapstruct.Mapper;
import ru.kpfu.itis.skillshare.mainservice.dto.response.MessageResponseDto;
import ru.kpfu.itis.skillshare.mainservice.model.MessageEntity;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    MessageResponseDto toResponse(MessageEntity messageEntity);

}
