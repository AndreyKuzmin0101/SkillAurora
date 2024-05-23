package ru.kpfu.itis.skillshare.mainservice.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.skillshare.mainservice.dto.response.CityResponse;
import ru.kpfu.itis.skillshare.mainservice.dto.response.CountryResponse;
import ru.kpfu.itis.skillshare.mainservice.service.GeoService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GeoServiceImpl implements GeoService {

    private final ObjectMapper objectMapper;

    public List<CountryResponse> getCountries(String name) {
        try {
            name = name.replace(" ", "%20");
            URL url = new URL("https://api.api-ninjas.com/v1/country?name=" + name + "&limit=30");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("X-Api-Key", "IjvPfX9/ADHk7cQKMRcJzg==H09QxcHSLmFc67gR");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                List<CountryResponse> countries = objectMapper.readValue(response.toString(), new TypeReference<List<CountryResponse>>(){});

                return countries;
            } else {
                throw new RuntimeException("Response code: " + responseCode);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to find countries. " + e.getMessage());
        }
    }

    public List<CityResponse> getCities(String name, String country) {
        try {
            URL url = new URL("https://api.api-ninjas.com/v1/city?name=" + name + "&country=" + country + "&limit=30");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("X-Api-Key", "IjvPfX9/ADHk7cQKMRcJzg==H09QxcHSLmFc67gR");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                List<CityResponse> cities = objectMapper.readValue(response.toString(), new TypeReference<List<CityResponse>>(){});

                return cities;
            } else {
                throw new RuntimeException("Response code: " + responseCode);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to find countries. " + e.getMessage());
        }
    }
}
