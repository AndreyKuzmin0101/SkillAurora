package ru.kpfu.itis.skillshare.mainservice.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.skillshare.mainservice.api.TagApi;
import ru.kpfu.itis.skillshare.mainservice.dto.response.TagResponseDto;
import ru.kpfu.itis.skillshare.mainservice.service.TagService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TagController implements TagApi {
    private final TagService tagService;

    @Override
    public List<TagResponseDto> getAllNotCustom() {
        return tagService.getAllNotCustom();
    }
}
