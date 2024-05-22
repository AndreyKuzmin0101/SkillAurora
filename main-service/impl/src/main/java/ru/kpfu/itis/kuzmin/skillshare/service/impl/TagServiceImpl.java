package ru.kpfu.itis.kuzmin.skillshare.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.TagRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.TagResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.exception.alreadyexitsts.TagAlreadyExistsException;
import ru.kpfu.itis.kuzmin.skillshare.mapper.TagMapper;
import ru.kpfu.itis.kuzmin.skillshare.model.TagEntity;
import ru.kpfu.itis.kuzmin.skillshare.repository.spring.TagSpringRepository;
import ru.kpfu.itis.kuzmin.skillshare.service.TagService;

import java.util.*;

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

    @Override
    public List<TagResponseDto> getAllNotCustom() {
        return tagRepository.findAllByCustom(false).stream()
                .map(tagMapper::toResponse)
                .toList();
    }

    @Override
    public List<TagEntity> getListUniqTagEntitiesAndSaveNonExistent(List<TagRequestDto> tags) {
        if (tags != null) {
            Set<TagRequestDto> setTags = new HashSet<>(tags);
            List<TagEntity> tagEntities = new ArrayList<>();

            for (TagRequestDto tagDto: setTags) {
                Optional<TagEntity> tagOptional = tagRepository.findByName(tagDto.name());
                if (tagOptional.isPresent()) {
                    tagEntities.add(tagOptional.get());
                } else {
                    TagEntity newSkill = tagMapper.toEntity(tagDto);
                    newSkill.setCustom(true);
                    newSkill = tagRepository.save(newSkill);
                    tagEntities.add(newSkill);
                }

            }
            return tagEntities;
        }
        return new ArrayList<>();
    }
}
