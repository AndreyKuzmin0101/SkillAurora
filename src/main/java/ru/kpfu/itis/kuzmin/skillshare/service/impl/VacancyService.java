package ru.kpfu.itis.kuzmin.skillshare.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.UserRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.VacancyRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.mapper.VacancyMapper;
import ru.kpfu.itis.kuzmin.skillshare.repository.VacancyRepository;

@Service
@RequiredArgsConstructor
public class VacancyService {

    private final VacancyRepository vacancyRepository;
    private final VacancyMapper vacancyMapper;

    public Long save(VacancyRequestDto vacancyDto) {
        return vacancyRepository
                .save(vacancyMapper.toEntity(vacancyDto))
                .getId();
    }
}
