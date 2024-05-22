package ru.kpfu.itis.skillshare.mainservice.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.skillshare.mainservice.dto.request.ResumeRequestDto;
import ru.kpfu.itis.skillshare.mainservice.mapper.ResumeMapper;
import ru.kpfu.itis.skillshare.mainservice.repository.spring.ResumeSpringRepository;
import ru.kpfu.itis.skillshare.mainservice.service.ResumeService;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeSpringRepository resumeRepository;
    private final ResumeMapper resumeMapper;

    @Override
    public Long save(ResumeRequestDto resumeDto) {
        return resumeRepository
                .save(resumeMapper.toEntity(resumeDto))
                .getId();
    }
}
