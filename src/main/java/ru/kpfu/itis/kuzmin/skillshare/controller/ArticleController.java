package ru.kpfu.itis.kuzmin.skillshare.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.ArticleRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.ArticleResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.UserResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.security.BaseUserDetails;
import ru.kpfu.itis.kuzmin.skillshare.service.ArticleService;

@Controller
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService service;

    @GetMapping("")
    public String getStream() {
        // TODO: подготовка модели
        return "stream";
    }

    @GetMapping("/articles/{id}")
    public String getArticleById(@PathVariable("id") String id, Model model) {
        //NumberFormatException
        Long articleId = Long.parseLong(id);
        ArticleResponseDto articleDto = service.getById(articleId);
        UserResponseDto userDto = service.getAuthor(articleId);

        model.addAttribute("article", articleDto);

        model.addAttribute("id", userDto.id());
        model.addAttribute("username", userDto.username());
        model.addAttribute("realName", userDto.realName());
        model.addAttribute("image", userDto.profileImage());

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
        Long articleId = service.save(userId, articleRequestDto);
        return "redirect:/article/%s".formatted(articleId);
    }
}
