package org.weather.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.weather.adapter.StormGlassAdapter;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.weather.adapter.StormGlassFakeAdapter;
import org.weather.adapter.WeatherAdapter;
import org.weather.dto.WeatherDTO;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final StormGlassAdapter stormGlassAdapter;
    private final StringRedisTemplate redisTemplate;
    private final StormGlassFakeAdapter stormGlassFakeAdapter;
    private final boolean useFakeAdapter;
    private static final Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);

    @Autowired
    public WeatherServiceImpl(
            StormGlassFakeAdapter stormGlassFakeAdapter,
            StormGlassAdapter stormGlassAdapter,
            StringRedisTemplate redisTemplate,
            @Value("${weather.useFakeAdapter}") boolean useFakeAdapter
    ) {
        this.stormGlassAdapter = stormGlassAdapter;
        this.redisTemplate = redisTemplate;
        this.stormGlassFakeAdapter = stormGlassFakeAdapter;
        this.useFakeAdapter = useFakeAdapter;

    }

    @Override
    public List<WeatherDTO> getWeatherData(String lat, String lng) {
        String cacheKey = "weather:" + lat + ":" + lng;
        logger.info("Fetching weather data for cache key: {}", cacheKey);
        logger.info("Using fake adapter: {}", useFakeAdapter);
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        List<WeatherDTO> weatherData = null;

        try {
            // Check cache first
            String cachedData = ops.get(cacheKey);
            if (cachedData != null) {
                logger.info("Returning cached data for: {}", cacheKey);
                weatherData = new ObjectMapper().readValue(cachedData, new TypeReference<List<WeatherDTO>>(){});
                return weatherData;
            }

            // Fetch from API if not cached
            WeatherAdapter adapter = useFakeAdapter ? stormGlassFakeAdapter : stormGlassAdapter;
            weatherData = adapter.getWeatherFromApi(lat, lng);
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
