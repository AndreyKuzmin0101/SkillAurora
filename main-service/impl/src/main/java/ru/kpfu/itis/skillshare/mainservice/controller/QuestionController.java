package ru.kpfu.itis.skillshare.mainservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.skillshare.mainservice.dto.request.QuestionFilter;
import ru.kpfu.itis.skillshare.mainservice.dto.request.QuestionRequestDto;
import ru.kpfu.itis.skillshare.mainservice.dto.response.QuestionResponseDto;
import ru.kpfu.itis.skillshare.mainservice.dto.response.UrlResponse;
import ru.kpfu.itis.skillshare.mainservice.exception.notfound.QuestionNotFoundException;
import ru.kpfu.itis.skillshare.mainservice.service.QuestionService;

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
    public List<QuestionResponseDto> getPageFilteredQuestions(@Validated QuestionFilter filter) {
        return questionService.getPageFiltered(filter);
    }

    @GetMapping("/questions/{id}")
    public String getQuestionById(@PathVariable("id") String id, Model model) {
        //NumberFormatException
        Long questionId = Long.parseLong(id);
        try {
            QuestionResponseDto questionDto = questionService.getById(questionId);
            model.addAttribute("question", questionDto);
            return "question";
        } catch (QuestionNotFoundException e) {
            return "not-found";
        }
    }

    @GetMapping("/create/question")
    public String askQuestion() {
        return "ask-question";
    }

    @PostMapping("/create/question")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public UrlResponse postQuestion(@Validated @RequestBody QuestionRequestDto questionRequest) {
        Long questionId = questionService.save(questionRequest);
        return new UrlResponse("/questions/%s".formatted(questionId));
    }

    @PutMapping("/questions/{id}/close")
    @ResponseStatus(HttpStatus.OK)
    public void closeQuestion(@PathVariable("id") Long questionId) {
        questionService.close(questionId);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/questions/{id}/is-author")
    public Boolean isAuthor(@PathVariable("id") Long questionId) {
        return questionService.isAuthor(questionId);
    }
}
