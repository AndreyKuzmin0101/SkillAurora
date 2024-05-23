package ru.kpfu.itis.skillshare.mainservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CountryResponse {
    @JsonProperty("iso2")
    private String iso2;
    @JsonProperty("name")
    private String name;
}
