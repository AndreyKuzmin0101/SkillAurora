package ru.kpfu.itis.skillshare.mainservice.service;

import com.fasterxml.jackson.core.type.TypeReference;
import ru.kpfu.itis.skillshare.mainservice.dto.response.CityResponse;
import ru.kpfu.itis.skillshare.mainservice.dto.response.CountryResponse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public interface GeoService {

    List<CountryResponse> getCountries(String name);

    List<CityResponse> getCities(String name, String country);
}
