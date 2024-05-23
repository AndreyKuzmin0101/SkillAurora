package ru.kpfu.itis.skillshare.mainservice.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.skillshare.mainservice.api.GeoApi;
import ru.kpfu.itis.skillshare.mainservice.dto.response.CityResponse;
import ru.kpfu.itis.skillshare.mainservice.dto.response.CountryResponse;
import ru.kpfu.itis.skillshare.mainservice.service.GeoService;
import ru.kpfu.itis.skillshare.mainservice.service.impl.GeoServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GeoController implements GeoApi {

    private final GeoService geoService;

    @Override
    public List<CountryResponse> getCountries(String name) {
        return geoService.getCountries(name);
    }

    @Override
    public List<CityResponse> getCities(String name, String country) {
        return geoService.getCities(name, country);
    }
}
