package org.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.weather.adapter.OpenWeatherMapAdapter;
import org.weather.dto.OpenWeatherDTO;
import org.weather.dto.WeatherDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class OpenWeatherServiceImpl implements OpenWeatherService {

    private final OpenWeatherMapAdapter openWeatherMapAdapter;
    private final StringRedisTemplate redisTemplate;
    private static final Logger logger = LoggerFactory.getLogger(OpenWeatherServiceImpl.class);

    @Autowired
    public OpenWeatherServiceImpl(
            OpenWeatherMapAdapter openWeatherMapAdapter,
            StringRedisTemplate redisTemplate
    ) {
        this.openWeatherMapAdapter = openWeatherMapAdapter;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<OpenWeatherDTO> getWeatherData(String lat, String lng) {
        String cacheKey = "weather:" + lat + ":" + lng;
        logger.info("Fetching weather data for cache key: {}", cacheKey);
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        List<OpenWeatherDTO> weatherData = null;

        try {
            // Check cache first
            String cachedData = ops.get(cacheKey);
            if (cachedData != null) {
                logger.info("Returning cached data for: {}", cacheKey);
                weatherData = new ObjectMapper().readValue(cachedData, new TypeReference<List<OpenWeatherDTO>>(){});
                return weatherData;
            }

            // Fetch from API if not cached
            weatherData = (List<OpenWeatherDTO>) openWeatherMapAdapter.getWeatherData(lat, lng);
            if (weatherData != null && !weatherData.isEmpty()) {
                String jsonWeatherData = new ObjectMapper().writeValueAsString(weatherData);
                ops.set(cacheKey, jsonWeatherData, 30, TimeUnit.MINUTES);  // cache for 30 minutes
                logger.info("Weather data cached for: {}", cacheKey);
            }
        } catch (Exception e) {
            logger.error("Error retrieving weather data: {}", e.getMessage());
            logger.error("Stack Trace:", e);
        }

        // Return the fetched data, or null if there was an error
        return weatherData;
    }
}
