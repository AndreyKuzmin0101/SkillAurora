package ru.kpfu.itis.skillshare.mainservice.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.skillshare.mainservice.dto.request.AnswerRequestDto;
import ru.kpfu.itis.skillshare.mainservice.dto.response.AnswerResponseDto;

import java.util.List;

@Tag(name = "Answers | Ответы", description = "API ответов на вопросы")
@RequestMapping("/api/v1/questions")
public interface AnswerApi {

    @Operation(
            summary = "Получить ответы на вопрос",
            description = "Позволяет получить все ответы на вопрос по его id"
    )
    @GetMapping("/{question-id}/answers")
    @ResponseStatus(HttpStatus.OK)
    List<AnswerResponseDto> getAnswersToQuestion(@PathVariable("question-id") Long questionId);

    @Operation(
            summary = "Пометить ответ лучшим",
            description = "Позволяет пометить ответ лучшим по его id"
    )
    @SecurityRequirement(name = "JWT")
    @PutMapping("/answers/{answer-id}/mark")
    @ResponseStatus(HttpStatus.OK)
    void markAnswerAsTheBest(@PathVariable("answer-id") Long answerId);

    @Operation(
            summary = "Снять метку с ответа",
            description = "Позволяет снять метку 'лучший' с ответа по его id"
    )
    @SecurityRequirement(name = "JWT")
    @PutMapping("/answers/{answer-id}/unmark")
    @ResponseStatus(HttpStatus.OK)
    void unmarkAnswer(@PathVariable("answer-id") Long answerId);

    @Operation(
            summary = "Ответить на вопрос",
            description = "Позволяет запостить новый ответ на вопрос"
    )
    @SecurityRequirement(name = "JWT")
    @PostMapping("/{question-id}/answers")
    @ResponseStatus(HttpStatus.CREATED)
    Long postAnswer(@PathVariable("question-id") Long questionId, AnswerRequestDto answerRequest);
}
