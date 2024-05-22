package ru.kpfu.itis.skillshare.mainservice.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.skillshare.mainservice.dto.request.ArticleFilter;
import ru.kpfu.itis.skillshare.mainservice.dto.request.ArticleRequestDto;
import ru.kpfu.itis.skillshare.mainservice.dto.response.ArticleResponseDto;
import ru.kpfu.itis.skillshare.mainservice.dto.response.UrlResponse;
import ru.kpfu.itis.skillshare.mainservice.security.util.SecurityUtil;
import ru.kpfu.itis.skillshare.mainservice.service.ArticleService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping()
    public String getStream() {
        System.out.println(1);
        return "stream";
    }

    @GetMapping("/articles/{id}")
    public String getArticleView() {
        return "article";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/v1/articles/{id}")
    @ResponseBody
    public ArticleResponseDto getArticleById(@PathVariable("id") String id) {
        //NumberFormatException
        Long articleId = Long.parseLong(id);
        return articleService.getById(articleId);
    }

    @GetMapping("/create/article")
    public String getCreateForm() {
        return "create-article";
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create/article")
    @ResponseBody
    public UrlResponse create(@RequestBody ArticleRequestDto articleRequestDto) {
        Long userId = SecurityUtil.getIdAuthenticatedUser();
        Long articleId = articleService.save(userId, articleRequestDto);

        return new UrlResponse("/articles/%s" .formatted(articleId));
    }

    @GetMapping("/articles/filter")
    @ResponseBody
    public List<ArticleResponseDto> getPageFilteredArticles(ArticleFilter filter) {
        return articleService.getPageFiltered(filter);
    }

}
