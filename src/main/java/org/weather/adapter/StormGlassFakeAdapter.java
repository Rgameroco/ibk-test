package org.weather.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.weather.dto.WeatherDTO;
import org.weather.exception.WeatherDataProcessingException;
import org.weather.exception.WeatherServiceException;
import org.weather.mapper.WeatherMapper;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Component
public class StormGlassFakeAdapter implements WeatherAdapter{
    private static final Logger logger = LoggerFactory.getLogger(StormGlassFakeAdapter.class);
    @Value("${weather.api.url}")
    private String apiUrl;

    @Value("${weather.api.key}")
    private String apiKey;
    @Override
    public List<WeatherDTO> getWeatherFromApi(String lat, String lng) {
        logger.info("Using FakeAdapter");
        try {

            String jsonData = "{\"hours\":[{\"airTemperature\":{\"noaa\":24.23,\"sg\":24.23},\"time\":\"2024-07-04T00:00:00+00:00\",\"waveHeight\":{\"meteo\":1.61,\"noaa\":1.47,\"sg\":1.61}},{\"airTemperature\":{\"noaa\":24.2,\"sg\":24.2},\"time\":\"2024-07-04T01:00:00+00:00\",\"waveHeight\":{\"meteo\":1.6,\"noaa\":1.45,\"sg\":1.6}},{\"airTemperature\":{\"noaa\":24.18,\"sg\":24.18},\"time\":\"2024-07-04T02:00:00+00:00\",\"waveHeight\":{\"meteo\":1.58,\"noaa\":1.42,\"sg\":1.58}},{\"airTemperature\":{\"noaa\":24.15,\"sg\":24.15},\"time\":\"2024-07-04T03:00:00+00:00\",\"waveHeight\":{\"meteo\":1.57,\"noaa\":1.4,\"sg\":1.57}},{\"airTemperature\":{\"noaa\":24.2,\"sg\":24.2},\"time\":\"2024-07-04T04:00:00+00:00\",\"waveHeight\":{\"meteo\":1.58,\"noaa\":1.41,\"sg\":1.58}},{\"airTemperature\":{\"noaa\":24.24,\"sg\":24.24},\"time\":\"2024-07-04T05:00:00+00:00\",\"waveHeight\":{\"meteo\":1.58,\"noaa\":1.41,\"sg\":1.58}},{\"airTemperature\":{\"noaa\":24.29,\"sg\":24.29},\"time\":\"2024-07-04T06:00:00+00:00\",\"waveHeight\":{\"meteo\":1.59,\"noaa\":1.42,\"sg\":1.59}},{\"airTemperature\":{\"noaa\":24.41,\"sg\":24.41},\"time\":\"2024-07-04T07:00:00+00:00\",\"waveHeight\":{\"meteo\":1.61,\"noaa\":1.39,\"sg\":1.61}},{\"airTemperature\":{\"noaa\":24.54,\"sg\":24.54},\"time\":\"2024-07-04T08:00:00+00:00\",\"waveHeight\":{\"meteo\":1.62,\"noaa\":1.36,\"sg\":1.62}},{\"airTemperature\":{\"noaa\":24.67,\"sg\":24.67},\"time\":\"2024-07-04T09:00:00+00:00\",\"waveHeight\":{\"meteo\":1.64,\"noaa\":1.33,\"sg\":1.64}},{\"airTemperature\":{\"noaa\":24.79,\"sg\":24.79},\"time\":\"2024-07-04T10:00:00+00:00\",\"waveHeight\":{\"meteo\":1.65,\"noaa\":1.35,\"sg\":1.65}},{\"airTemperature\":{\"noaa\":24.92,\"sg\":24.92},\"time\":\"2024-07-04T11:00:00+00:00\",\"waveHeight\":{\"meteo\":1.66,\"noaa\":1.37,\"sg\":1.66}},{\"airTemperature\":{\"noaa\":25.05,\"sg\":25.05},\"time\":\"2024-07-04T12:00:00+00:00\",\"waveHeight\":{\"meteo\":1.67,\"noaa\":1.39,\"sg\":1.67}},{\"airTemperature\":{\"noaa\":25.09,\"sg\":25.09},\"time\":\"2024-07-04T13:00:00+00:00\",\"waveHeight\":{\"meteo\":1.66,\"noaa\":1.38,\"sg\":1.66}},{\"airTemperature\":{\"noaa\":25.13,\"sg\":25.13},\"time\":\"2024-07-04T14:00:00+00:00\",\"waveHeight\":{\"meteo\":1.66,\"noaa\":1.38,\"sg\":1.66}},{\"airTemperature\":{\"noaa\":25.17,\"sg\":25.17},\"time\":\"2024-07-04T15:00:00+00:00\",\"waveHeight\":{\"meteo\":1.65,\"noaa\":1.37,\"sg\":1.65}},{\"airTemperature\":{\"noaa\":25.09,\"sg\":25.09},\"time\":\"2024-07-04T16:00:00+00:00\",\"waveHeight\":{\"meteo\":1.63,\"noaa\":1.37,\"sg\":1.63}},{\"airTemperature\":{\"noaa\":25,\"sg\":25},\"time\":\"2024-07-04T17:00:00+00:00\",\"waveHeight\":{\"meteo\":1.62,\"noaa\":1.38,\"sg\":1.62}},{\"airTemperature\":{\"noaa\":24.92,\"sg\":24.92},\"time\":\"2024-07-04T18:00:00+00:00\",\"waveHeight\":{\"meteo\":1.6,\"noaa\":1.38,\"sg\":1.6}},{\"airTemperature\":{\"noaa\":24.85,\"sg\":24.85},\"time\":\"2024-07-04T19:00:00+00:00\",\"waveHeight\":{\"meteo\":1.59,\"noaa\":1.38,\"sg\":1.59}},{\"airTemperature\":{\"noaa\":24.78,\"sg\":24.78},\"time\":\"2024-07-04T20:00:00+00:00\",\"waveHeight\":{\"meteo\":1.57,\"noaa\":1.37,\"sg\":1.57}},{\"airTemperature\":{\"noaa\":24.72,\"sg\":24.72},\"time\":\"2024-07-04T21:00:00+00:00\",\"waveHeight\":{\"meteo\":1.56,\"noaa\":1.37,\"sg\":1.56}},{\"airTemperature\":{\"noaa\":24.67,\"sg\":24.67},\"time\":\"2024-07-04T22:00:00+00:00\",\"waveHeight\":{\"meteo\":1.56,\"noaa\":1.4,\"sg\":1.56}},{\"airTemperature\":{\"noaa\":24.61,\"sg\":24.61},\"time\":\"2024-07-04T23:00:00+00:00\",\"waveHeight\":{\"meteo\":1.56,\"noaa\":1.42,\"sg\":1.56}}],\"meta\":{\"cost\":1,\"dailyQuota\":10,\"end\":\"2024-07-04 23:59\",\"lat\":30.7983,\"lng\":18.8003,\"params\":[\"waveHeight\",\"airTemperature\"],\"requestCount\":8,\"start\":\"2024-07-04 00:00\"}}";
            ResponseEntity<String> response = ResponseEntity.ok(jsonData);
            JsonNode root = new ObjectMapper().readTree(response.getBody());
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