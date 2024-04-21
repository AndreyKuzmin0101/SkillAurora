package ru.kpfu.itis.kuzmin.skillshare.service;

import ru.kpfu.itis.kuzmin.skillshare.dto.request.TagRequestDto;

public interface TagService {
    Integer save(TagRequestDto tagDto);
}
