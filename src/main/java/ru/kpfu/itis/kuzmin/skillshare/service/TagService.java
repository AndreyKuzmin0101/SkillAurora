package ru.kpfu.itis.kuzmin.skillshare.service;

import ru.kpfu.itis.kuzmin.skillshare.dto.request.TagRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.TagResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.model.TagEntity;

import java.util.List;

public interface TagService {
    Integer save(TagRequestDto tagDto);

    List<TagResponseDto> getAllNotCustom();

    List<TagEntity> getListUniqTagEntitiesAndSaveNonExistent(List<TagRequestDto> tags);
}
