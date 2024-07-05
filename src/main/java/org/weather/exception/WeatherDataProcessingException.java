package org.weather.exception;

public class WeatherDataProcessingException extends RuntimeException {
    public WeatherDataProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
