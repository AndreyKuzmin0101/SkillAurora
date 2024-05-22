package ru.kpfu.itis.kuzmin.skillshare.service;

import ru.kpfu.itis.kuzmin.skillshare.dto.request.ResumeRequestDto;

public interface ResumeService {
    Long save(ResumeRequestDto resumeDto);
}
