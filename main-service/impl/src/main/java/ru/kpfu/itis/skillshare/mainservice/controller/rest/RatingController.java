package ru.kpfu.itis.skillshare.mainservice.controller.rest;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.skillshare.mainservice.api.RatingApi;
import ru.kpfu.itis.skillshare.mainservice.service.RatingService;

@RestController
@RequiredArgsConstructor
public class RatingController implements RatingApi {

    private final RatingService ratingService;

    @Override
    public Integer getMyRatingForArticle(Long id) {
        return ratingService.getRatingByCurrUserForArticle(id);
    }

    @Override
    public Long plusRating(Long id) {
        ratingService.plusToArticle(id);
        return ratingService.getArticleRating(id);
    }

    @Override
    public Long minusRating(Long id) {
        ratingService.minusToArticle(id);
        return ratingService.getArticleRating(id);
    }
}
