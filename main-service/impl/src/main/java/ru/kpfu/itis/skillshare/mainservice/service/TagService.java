package ru.kpfu.itis.skillshare.mainservice.service;

import ru.kpfu.itis.skillshare.mainservice.dto.request.TagRequestDto;
import ru.kpfu.itis.skillshare.mainservice.dto.response.TagResponseDto;
import ru.kpfu.itis.skillshare.mainservice.model.TagEntity;

import java.util.List;

public interface TagService {
    Integer save(TagRequestDto tagDto);

    List<TagResponseDto> getAllNotCustom();

    List<TagEntity> getListUniqTagEntitiesAndSaveNonExistent(List<TagRequestDto> tags);
}
