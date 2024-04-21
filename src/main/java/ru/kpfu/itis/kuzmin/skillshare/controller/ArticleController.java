package ru.kpfu.itis.kuzmin.skillshare.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.ArticleRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.ArticleResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.service.ArticleService;

@Controller
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService service;

    @GetMapping("/article/{id}")
    public String getArticleById(@PathVariable("id") String id, Model model) {
        //NumberFormatException
        Long articleId = Long.parseLong(id);
        ArticleResponseDto articleDto = service.getById(articleId);
        model.addAttribute("article", articleDto);
        return "article";
    }

    @GetMapping("/create/article")
    public String getCreateForm() {
        return "create-article";
    }


    @PostMapping("/create/article")
    public String create(ArticleRequestDto articleRequestDto) {
        Long articleId = service.save(articleRequestDto);
        return "redirect:/article/%s".formatted(articleId);
    }
}
