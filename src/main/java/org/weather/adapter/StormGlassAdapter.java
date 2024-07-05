package org.weather.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.weather.dto.WeatherDTO;
import org.weather.exception.WeatherDataProcessingException;
import org.weather.exception.WeatherServiceException;
import org.weather.mapper.WeatherMapper;

@Component
public class StormGlassAdapter implements WeatherAdapter {
    private static final Logger logger = LoggerFactory.getLogger(StormGlassAdapter.class);
    @Value("${weather.api.url}")
    private String apiUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    @Override
    public List<WeatherDTO> getWeatherFromApi(String lat, String lng) {
        logger.info("Using Adapter");
        try {
            String parameters = "waveHeight,airTemperature";
            Instant now = Instant.now();
            long start = now.truncatedTo(ChronoUnit.DAYS).getEpochSecond();
            long end = now.plus(1, ChronoUnit.DAYS).truncatedTo(ChronoUnit.DAYS).getEpochSecond() - 1;

            String endpoint = String.format("%s/weather/point?lat=%s&lng=%s&params=%s&start=%d&end=%d", apiUrl, lat, lng, parameters, start, end);
            logger.info(endpoint);
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", apiKey);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(endpoint, HttpMethod.GET, entity, String.class);
            JsonNode root = new ObjectMapper().readTree(response.getBody());
            logger.info("Test");
            JsonNode hours = root.path("hours");
            return getWeatherDTOS(hours);
        } catch (HttpClientErrorException e) {
            throw new WeatherServiceException("API request failed: " + e.getMessage(), e);
        } catch (JsonProcessingException e) {
            throw new WeatherDataProcessingException("JSON parsing error", e);
        } catch (Exception e) {
            throw new WeatherServiceException("Unexpected error occurred", e);
        }
    }
    static List<WeatherDTO> getWeatherDTOS(JsonNode hours) {
        List<WeatherDTO> weatherData = new ArrayList<>();

        hours.forEach(hour -> {
            try {
                weatherData.add(WeatherMapper.jsonNodeToWeatherDTO(hour));
            } catch (Exception e) {
                throw new WeatherDataProcessingException("Error processing weather data", e);
            }
        });

        return weatherData;
    }
}