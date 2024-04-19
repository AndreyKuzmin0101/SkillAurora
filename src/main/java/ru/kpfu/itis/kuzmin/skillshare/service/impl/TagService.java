package ru.kpfu.itis.kuzmin.skillshare.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.TagRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.UserRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.exception.alreadyexitsts.TagAlreadyExistsException;
import ru.kpfu.itis.kuzmin.skillshare.mapper.TagMapper;
import ru.kpfu.itis.kuzmin.skillshare.repository.TagRepository;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public Integer save(TagRequestDto tagDto) {
        if (tagRepository.findByName(tagDto.name()).isPresent()) {
            throw new TagAlreadyExistsException(tagDto.name());
        }

        return tagRepository
                .save(tagMapper.toEntity(tagDto))
                .getId();
    }
}
