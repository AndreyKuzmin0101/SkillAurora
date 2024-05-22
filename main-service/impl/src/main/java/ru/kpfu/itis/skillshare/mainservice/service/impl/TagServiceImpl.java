package ru.kpfu.itis.skillshare.mainservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.skillshare.mainservice.dto.request.TagRequestDto;
import ru.kpfu.itis.skillshare.mainservice.dto.response.TagResponseDto;
import ru.kpfu.itis.skillshare.mainservice.exception.alreadyexitsts.TagAlreadyExistsException;
import ru.kpfu.itis.skillshare.mainservice.mapper.TagMapper;
import ru.kpfu.itis.skillshare.mainservice.model.TagEntity;
import ru.kpfu.itis.skillshare.mainservice.repository.spring.TagSpringRepository;
import ru.kpfu.itis.skillshare.mainservice.service.TagService;

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
