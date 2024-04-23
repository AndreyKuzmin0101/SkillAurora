package ru.kpfu.itis.kuzmin.skillshare.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.TagRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.exception.alreadyexitsts.TagAlreadyExistsException;
import ru.kpfu.itis.kuzmin.skillshare.mapper.TagMapper;
import ru.kpfu.itis.kuzmin.skillshare.repository.spring.TagSpringRepository;
import ru.kpfu.itis.kuzmin.skillshare.service.TagService;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagSpringRepository tagRepository;
    private final TagMapper tagMapper;

    @Override
    public Integer save(TagRequestDto tagDto) {
        if (tagRepository.findByName(tagDto.name()).isPresent()) {
            throw new TagAlreadyExistsException(tagDto.name());
        }

        return tagRepository
                .save(tagMapper.toEntity(tagDto))
                .getId();
    }
}
