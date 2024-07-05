package org.weather.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.weather.dto.WeatherDTO;

import java.util.HashMap;
import java.util.Map;

public class WeatherMapper {
    private static final Logger logger = LoggerFactory.getLogger(WeatherMapper.class);

    public static WeatherDTO jsonNodeToWeatherDTO(JsonNode node) {
        WeatherDTO dto = new WeatherDTO();

        if (node.has("time")) {
            dto.setTime(node.get("time").asText());
        }

        if (node.has("airTemperature") && node.get("airTemperature").isObject()) {
            Map<String, Double> airTemperature = new HashMap<>();
            node.get("airTemperature").fields().forEachRemaining(entry -> {
                airTemperature.put(entry.getKey(), entry.getValue().asDouble());
            });
            dto.setAirTemperature(airTemperature);
        }

        if (node.has("waveHeight") && node.get("waveHeight").isObject()) {
            Map<String, Double> waveHeight = new HashMap<>();
            node.get("waveHeight").fields().forEachRemaining(entry -> {
                waveHeight.put(entry.getKey(), entry.getValue().asDouble());
            });
            dto.setWaveHeight(waveHeight);
        }

        return dto;
    }
}
