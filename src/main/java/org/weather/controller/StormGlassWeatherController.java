package org.weather.controller;

import org.weather.dto.WeatherDTO;
import org.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/weather")
public class StormGlassWeatherController {

    private final WeatherService weatherService;

    @Autowired
    public StormGlassWeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public ResponseEntity<?> getWeather(@RequestParam String lat, @RequestParam String lng) {
        try {
            List<WeatherDTO> weatherData = weatherService.getWeatherData(lat, lng);
            return ResponseEntity.ok(weatherData);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error retrieving weather data: " + e.getMessage());
        }
    }
}
