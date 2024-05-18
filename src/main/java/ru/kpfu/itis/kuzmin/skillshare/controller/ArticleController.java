package ru.kpfu.itis.kuzmin.skillshare.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.kuzmin.skillshare.dto.Roles;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.ArticleFilter;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.ArticleRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.ArticleResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.UrlResponse;
import ru.kpfu.itis.kuzmin.skillshare.security.JwtTokenAuthentication;
import ru.kpfu.itis.kuzmin.skillshare.security.exception.AuthenticationHeaderException;
import ru.kpfu.itis.kuzmin.skillshare.security.exception.AuthorizationException;
import ru.kpfu.itis.kuzmin.skillshare.service.ArticleService;
import ru.kpfu.itis.kuzmin.skillshare.security.util.SecurityUtil;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping()
    public String getStream() {
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

        return new UrlResponse("/articles/%s".formatted(articleId));
    }

    @GetMapping("/articles/filter")
    @ResponseBody
    public List<ArticleResponseDto> getPageFilteredArticles (ArticleFilter filter) {
        return articleService.getPageFiltered(filter);
    }

}
