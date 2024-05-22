package ru.kpfu.itis.skillshare.mainservice.service;

import ru.kpfu.itis.skillshare.mainservice.dto.request.VacancyRequestDto;

public interface VacancyService {
    Long save(VacancyRequestDto vacancyDto);
}
