package ru.kpfu.itis.skillshare.mainservice.service;

import ru.kpfu.itis.skillshare.mainservice.dto.request.ResumeRequestDto;

public interface ResumeService {
    Long save(ResumeRequestDto resumeDto);
}
