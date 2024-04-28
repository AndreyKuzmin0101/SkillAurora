package ru.kpfu.itis.kuzmin.skillshare.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.ArticleFilter;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.ArticleRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.ArticleResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.UserResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.security.BaseUserDetails;
import ru.kpfu.itis.kuzmin.skillshare.service.ArticleService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("")
    public String getStream() {
        // TODO: подготовка модели
        return "stream";
    }

    @GetMapping("/articles/{id}")
    public String getArticleById(@PathVariable("id") String id, Model model) {
        //NumberFormatException
        Long articleId = Long.parseLong(id);
        ArticleResponseDto articleDto = articleService.getById(articleId);
        UserResponseDto userDto = articleService.getAuthor(articleId);

        model.addAttribute("article", articleDto);
        model.addAttribute("author", userDto);

        return "article";
    }

    @GetMapping("/create/article")
    public String getCreateForm() {
        return "create-article";
    }


    @PostMapping("/create/article")
    public String create(ArticleRequestDto articleRequestDto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = ((BaseUserDetails) principal).getUser().getId();
        Long articleId = articleService.save(userId, articleRequestDto);
        return "redirect:/article/%s".formatted(articleId);
    }

    // url: /articles/filter?page={page}&size={size}
    @GetMapping("/articles/filter")
    @ResponseBody
    public List<ArticleResponseDto> getPageFilteredArticles (ArticleFilter filter) {
        return articleService.getPageFiltered(filter);
    }

}
