package ru.kpfu.itis.kuzmin.skillshare.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.ArticleResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.service.impl.ArticleService;

@Controller
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService service;

    @GetMapping("/article/{id}")
    public String getArticleById(@RequestParam("id") String id, Model model) {
        //NumberFormatException
        Long articleId = Long.parseLong(id);
        ArticleResponseDto articleDto = service.getById(articleId);
        model.addAttribute("article", articleDto);
        return "article";
    }
}
