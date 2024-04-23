package ru.kpfu.itis.kuzmin.skillshare.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.VacancyRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.mapper.VacancyMapper;
import ru.kpfu.itis.kuzmin.skillshare.repository.spring.VacancySpringRepository;
import ru.kpfu.itis.kuzmin.skillshare.service.VacancyService;

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
