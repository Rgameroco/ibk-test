package org.weather.controller;

import org.weather.dto.OpenWeatherDTO;
import org.weather.dto.WeatherDTO;
import org.weather.service.OpenWeatherService; // Asegúrate de que el servicio esté implementado adecuadamente
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/openweather")
public class OpenWeatherController {

    private final OpenWeatherService openWeatherService;

    @Autowired
    public OpenWeatherController(OpenWeatherService openWeatherService) {
        this.openWeatherService = openWeatherService;
    }

    @GetMapping
    public ResponseEntity<?> getWeather(@RequestParam String lat, @RequestParam String lng) {
        try {
            List<OpenWeatherDTO> weatherData = openWeatherService.getWeatherData(lat, lng);
            return ResponseEntity.ok(weatherData);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error retrieving weather data: " + e.getMessage());
        }
    }
}
