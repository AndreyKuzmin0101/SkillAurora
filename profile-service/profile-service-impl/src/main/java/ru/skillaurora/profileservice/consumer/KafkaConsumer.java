package ru.skillaurora.profileservice.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.skillaurora.profileservice.dto.request.RatingUpdateRequest;
import ru.skillaurora.profileservice.service.ProfileInfoService;

@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final ProfileInfoService profileInfoService;

    @KafkaListener(id = "ratings", topics = {"server.ratings"}, containerFactory = "singleFactory")
    public void processTopic(RatingUpdateRequest ratingUpdateRequest) {
        profileInfoService.updateRating(ratingUpdateRequest);
    }
}
