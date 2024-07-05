package org.weather.exception;

public class InvalidWeatherParametersException extends RuntimeException {
    public InvalidWeatherParametersException(String message) {
        super(message);
    }
}
