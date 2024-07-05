package org.weather.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.weather.dto.OpenWeatherDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.weather.service.OpenWeatherServiceImpl;

import java.util.Collections;
import java.util.List;

@Component
public class OpenWeatherMapAdapter {

    @Value("${openweather.api.url}")
    private String apiUrl;

    @Value("${openweather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(OpenWeatherMapAdapter.class);

    @Autowired
    public OpenWeatherMapAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<OpenWeatherDTO> getWeatherData(String lat, String lon) {
        String url = String.format("%s?lat=%s&lon=%s&appid=%s&units=metric", apiUrl, lat, lon, apiKey);
        logger.info(url);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<OpenWeatherDTO> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                OpenWeatherDTO.class
        );

        return Collections.singletonList(response.getBody());
    }
}
