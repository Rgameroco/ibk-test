package org.weather.service;

import org.weather.dto.OpenWeatherDTO;

import java.util.List;

public interface OpenWeatherService {
    /**
     * Recupera los datos meteorológicos basados en la latitud y longitud proporcionadas.
     *
     * @param lat La latitud para la cual se solicitan los datos meteorológicos.
     * @param lng La longitud para la cual se solicitan los datos meteorológicos.
     * @return Una lista de objetos WeatherDTO conteniendo los datos meteorológicos.
     */
    List<OpenWeatherDTO> getWeatherData(String lat, String lng);
}
