package org.weather.service;


import org.weather.dto.WeatherDTO;

import java.util.List;

public interface WeatherService {
    List<WeatherDTO> getWeatherData(String lat, String lng);
}