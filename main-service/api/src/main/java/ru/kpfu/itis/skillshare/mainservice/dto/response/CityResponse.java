package ru.kpfu.itis.skillshare.mainservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CityResponse {
    @JsonProperty("name")
    private String name;
}
