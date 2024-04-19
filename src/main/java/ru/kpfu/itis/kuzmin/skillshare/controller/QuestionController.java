package ru.kpfu.itis.kuzmin.skillshare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.ArticleResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.QuestionResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.service.impl.QuestionService;

@Controller
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService service;

    @GetMapping("/article/{id}")
    public String getQuestionById(@RequestParam("id") String id, Model model) {
        //NumberFormatException
        Long questionId = Long.parseLong(id);
        QuestionResponseDto questionDto = service.getById(questionId);
        model.addAttribute("article", questionDto);
        return "question";
    }

}
