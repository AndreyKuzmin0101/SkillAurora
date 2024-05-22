package ru.kpfu.itis.kuzmin.skillshare.mapper;

import org.mapstruct.Mapper;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.MessageResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.QuestionResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.model.MessageEntity;
import ru.kpfu.itis.kuzmin.skillshare.model.QuestionEntity;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    MessageResponseDto toResponse(MessageEntity messageEntity);

}
