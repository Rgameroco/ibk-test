package org.weather.adapter;

import org.weather.dto.WeatherDTO;

import java.util.List;

public interface WeatherAdapter  {
    List<WeatherDTO> getWeatherFromApi(String lat, String lng);
}
