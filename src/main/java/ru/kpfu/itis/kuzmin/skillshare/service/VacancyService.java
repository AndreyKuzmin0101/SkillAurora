package ru.kpfu.itis.kuzmin.skillshare.service;

import ru.kpfu.itis.kuzmin.skillshare.dto.request.VacancyRequestDto;

public interface VacancyService {
    Long save(VacancyRequestDto vacancyDto);
}
