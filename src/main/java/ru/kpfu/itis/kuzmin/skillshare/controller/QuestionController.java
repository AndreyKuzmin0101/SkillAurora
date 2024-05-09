package ru.kpfu.itis.kuzmin.skillshare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.QuestionFilter;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.QuestionRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.QuestionResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.UrlResponse;
import ru.kpfu.itis.kuzmin.skillshare.service.QuestionService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/questions")
    public String getQuestionsStream() {
        return "questions";
    }

    @GetMapping("/questions/filter")
    @ResponseBody
    public List<QuestionResponseDto> getPageFilteredQuestions(QuestionFilter filter) {
        return questionService.getPageFiltered(filter);
    }

    @GetMapping("/questions/{id}")
    public String getQuestionById(@PathVariable("id") String id, Model model) {
        //NumberFormatException
        Long questionId = Long.parseLong(id);
        QuestionResponseDto questionDto = questionService.getById(questionId);
        model.addAttribute("article", questionDto);
        return "question";
    }

    @GetMapping("/create/question")
    public String askQuestion() {
        return "ask-question";
    }

    @PostMapping("/create/question")
    @ResponseBody
    public UrlResponse postQuestion(@RequestBody QuestionRequestDto questionRequest) {
        Long questionId = questionService.save(questionRequest);
        return new UrlResponse("/question/%s".formatted(questionId));
    }

    @PutMapping("/questions/{id}/close")
    public void closeQuestion(@PathVariable("id") Long questionId) {
        questionService.close(questionId);
    }

}
