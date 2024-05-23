package ru.kpfu.itis.skillshare.mainservice.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.skillshare.mainservice.dto.response.CityResponse;
import ru.kpfu.itis.skillshare.mainservice.dto.response.CountryResponse;

import java.util.List;

@RequestMapping("/api/v1")
public interface GeoApi {

    @GetMapping("/countries")
    List<CountryResponse> getCountries(@RequestParam("name") String name);

    @GetMapping("/cities")
    List<CityResponse> getCities(@RequestParam("name") String name, @RequestParam("country") String country);
}
