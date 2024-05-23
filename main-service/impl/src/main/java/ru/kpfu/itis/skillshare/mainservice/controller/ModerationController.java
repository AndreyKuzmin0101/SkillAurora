package ru.kpfu.itis.skillshare.mainservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.skillshare.mainservice.dto.response.ArticleResponseDto;
import ru.kpfu.itis.skillshare.mainservice.service.ArticleService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ModerationController {

    private final ArticleService articleService;

    @GetMapping("/moderation")
    public String getModerationView() {
        return "moderation";
    }

    @GetMapping("/api/v1/articles/moderation")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<ArticleResponseDto> getPageWaitingArticles(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return articleService.getPageWaiting(page, size);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/api/v1/articles/{article-id}/moderation/confirm")
    public void confirmArticle (@PathVariable("article-id") Long articleId) {
        articleService.setModerationStatus(articleId, "confirmed");
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/api/v1/articles/{article-id}/moderation/reject")
    public void rejectArticle (@PathVariable("article-id") Long articleId) {
        articleService.setModerationStatus(articleId, "rejected");
    }
}
