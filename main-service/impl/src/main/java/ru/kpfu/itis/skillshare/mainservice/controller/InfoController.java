package ru.kpfu.itis.skillshare.mainservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InfoController {

    @GetMapping("/info/rating")
    public String getRatingInfo() {
        return "rating-info";
    }
}
