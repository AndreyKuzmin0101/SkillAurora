package ru.kpfu.itis.skillshare.mainservice.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.skillshare.mainservice.api.AnswerApi;
import ru.kpfu.itis.skillshare.mainservice.dto.request.AnswerRequestDto;
import ru.kpfu.itis.skillshare.mainservice.dto.response.AnswerResponseDto;
import ru.kpfu.itis.skillshare.mainservice.service.AnswerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AnswerController implements AnswerApi {

    private final AnswerService answerService;

    @Override
    public List<AnswerResponseDto> getAnswersToQuestion(Long questionId) {
        return answerService.getAllByQuestionId(questionId);
    }

    @Override
    public void markAnswerAsTheBest(Long answerId) {
        answerService.markAsTheBest(answerId);
    }

    @Override
    public void unmarkAnswer(Long answerId) {
        answerService.unmarkTheBest(answerId);
    }

    @Override
    public Long postAnswer(Long questionId,
                           @Validated AnswerRequestDto answerRequest) {
        return answerService.save(questionId, answerRequest);
    }
}
