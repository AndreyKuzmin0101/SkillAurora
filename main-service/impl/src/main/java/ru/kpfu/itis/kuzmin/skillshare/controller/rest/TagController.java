package ru.kpfu.itis.kuzmin.skillshare.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.TagResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.service.TagService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @GetMapping("/not-custom")
    public List<TagResponseDto> getAllNotCustom() {
        return tagService.getAllNotCustom();
    }
}
