package ru.kpfu.itis.kuzmin.skillshare.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.kuzmin.skillshare.dto.request.AnswerRequestDto;
import ru.kpfu.itis.kuzmin.skillshare.dto.response.AnswerResponseDto;
import ru.kpfu.itis.kuzmin.skillshare.service.AnswerService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/answers")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @GetMapping("/{question-id}")
    @ResponseStatus(HttpStatus.OK)
    public List<AnswerResponseDto> getAnswersToQuestion(@PathVariable("question-id") Long questionId) {
        return answerService.getAllByQuestionId(questionId);
    }


    @PutMapping("/{answer-id}/mark")
    @ResponseStatus(HttpStatus.OK)
    public void markAnswerAsTheBest(@PathVariable("answer-id") Long answerId) {
        answerService.markAsTheBest(answerId);
    }

    @PutMapping("/{answer-id}/unmark")
    @ResponseStatus(HttpStatus.OK)
    public void unmarkAnswer(@PathVariable("answer-id") Long answerId) {
        answerService.unmarkTheBest(answerId);
    }

    @PostMapping("/create/{question-id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Long postAnswer(@PathVariable("question-id") Long questionId, AnswerRequestDto answerRequest) {
        return answerService.save(questionId, answerRequest);
    }


}
