package ru.kpfu.itis.kuzmin.skillshare.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.kuzmin.skillshare.service.RatingService;

@RestController
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @GetMapping("/rating/{id}/me")
    @ResponseStatus(HttpStatus.OK)
    public Integer getMyRatingForArticle(@PathVariable("id") Long id) {
        return ratingService.getRatingByCurrUserForArticle(id);
    }

    @PostMapping("/rating/{id}/plus")
    @ResponseStatus(HttpStatus.OK)
    public Long plusRating(@PathVariable("id") Long id) {
        ratingService.plusToArticle(id);
        return ratingService.getArticleRating(id);
    }

    @PostMapping("/rating/{id}/minus")
    @ResponseStatus(HttpStatus.OK)
    public Long minusRating(@PathVariable("id") Long id) {
        ratingService.minusToArticle(id);
        return ratingService.getArticleRating(id);
    }
}
