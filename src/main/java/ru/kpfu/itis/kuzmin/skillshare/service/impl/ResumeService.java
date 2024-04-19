package ru.kpfu.itis.kuzmin.skillshare.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.ResumeRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.UserRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.mapper.ResumeMapper;
import ru.kpfu.itis.kuzmin.skillshare.repository.ResumeRepository;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final ResumeMapper resumeMapper;

    public Long save(ResumeRequestDto resumeDto) {
        return resumeRepository
                .save(resumeMapper.toEntity(resumeDto))
                .getId();
    }
}
