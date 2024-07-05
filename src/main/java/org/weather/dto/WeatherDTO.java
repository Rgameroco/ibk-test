package org.weather.dto;
import java.util.Map;


public class WeatherDTO {
    private String time;
    private Map<String, Double> airTemperature;
    private Map<String, Double> waveHeight;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Map<String, Double> getAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(Map<String, Double> airTemperature) {
        this.airTemperature = airTemperature;
    }

    public Map<String, Double> getWaveHeight() {
        return waveHeight;
    }

    public void setWaveHeight(Map<String, Double> waveHeight) {
        this.waveHeight = waveHeight;
    }
}