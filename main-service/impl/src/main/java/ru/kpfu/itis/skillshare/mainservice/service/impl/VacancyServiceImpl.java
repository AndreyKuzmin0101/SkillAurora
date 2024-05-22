package ru.kpfu.itis.skillshare.mainservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.skillshare.mainservice.dto.request.VacancyRequestDto;
import ru.kpfu.itis.skillshare.mainservice.mapper.VacancyMapper;
import ru.kpfu.itis.skillshare.mainservice.repository.spring.VacancySpringRepository;
import ru.kpfu.itis.skillshare.mainservice.service.VacancyService;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {

    private final VacancySpringRepository vacancyRepository;
    private final VacancyMapper vacancyMapper;

    @Override
    public Long save(VacancyRequestDto vacancyDto) {
        return vacancyRepository
                .save(vacancyMapper.toEntity(vacancyDto))
                .getId();
    }
}
