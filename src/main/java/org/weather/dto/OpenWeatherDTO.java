package org.weather.dto;

import java.util.List;
import java.util.Map;

public class OpenWeatherDTO {
    private Coordinates coord;
    private List<Weather> weather;
    private String base;
    private Main main;
    private Integer visibility;
    private Wind wind;
    private Clouds clouds;
    private Rain rain;
    private Snow snow;
    private Integer dt;
    private Sys sys;
    private Integer timezone;
    private Integer id;
    private String name;
    private Integer cod;

    public Coordinates getCoord() {
        return coord;
    }

    public void setCoord(Coordinates coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public Snow getSnow() {
        return snow;
    }

    public void setSnow(Snow snow) {
        this.snow = snow;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Integer getTimezone() {
        return timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }
// Getters and Setters

    public static class Weather {
        private Integer id;
        private String main;
        private String description;
        private String icon;

        // Getters and Setters

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }

    public static class Main {
        private Double temp;
        private Double feels_like;
        private Double temp_min;
        private Double temp_max;
        private Integer pressure;
        private Integer humidity;

        // Getters and Setters

        public Double getTemp() {
            return temp;
        }

        public void setTemp(Double temp) {
            this.temp = temp;
        }

        public Double getFeels_like() {
            return feels_like;
        }

        public void setFeels_like(Double feels_like) {
            this.feels_like = feels_like;
        }

        public Double getTemp_min() {
            return temp_min;
        }

        public void setTemp_min(Double temp_min) {
            this.temp_min = temp_min;
        }

        public Double getTemp_max() {
            return temp_max;
        }

        public void setTemp_max(Double temp_max) {
            this.temp_max = temp_max;
        }

        public Integer getPressure() {
            return pressure;
        }

        public void setPressure(Integer pressure) {
            this.pressure = pressure;
        }

        public Integer getHumidity() {
            return humidity;
        }

        public void setHumidity(Integer humidity) {
            this.humidity = humidity;
        }
    }

    public static class Wind {
        private Double speed;
        private Integer deg;

        // Getters and Setters

        public Double getSpeed() {
            return speed;
        }

        public void setSpeed(Double speed) {
            this.speed = speed;
        }

        public Integer getDeg() {
            return deg;
        }

        public void setDeg(Integer deg) {
            this.deg = deg;
        }
    }

    public static class Clouds {
        private Integer all;

        // Getters and Setters

        public Integer getAll() {
            return all;
        }

        public void setAll(Integer all) {
            this.all = all;
        }
    }

    public static class Rain {
        private Double _1h;
        private Double _3h;

        // Getters and Setters

        public Double get_3h() {
            return _3h;
        }

        public void set_3h(Double _3h) {
            this._3h = _3h;
        }

        public Double get_1h() {
            return _1h;
        }

        public void set_1h(Double _1h) {
            this._1h = _1h;
        }
    }

    public static class Snow {
        private Double _1h;
        private Double _3h;

        // Getters and Setters
        public Double get_3h() {
            return _3h;
        }

        public void set_3h(Double _3h) {
            this._3h = _3h;
        }

        public Double get_1h() {
            return _1h;
        }

        public void set_1h(Double _1h) {
            this._1h = _1h;
        }
    }

    public static class Sys {
        private Integer type;
        private Integer id;
        private Double message;
        private String country;
        private Long sunrise;
        private Long sunset;

        // Getters and Setters

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Double getMessage() {
            return message;
        }

        public void setMessage(Double message) {
            this.message = message;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public Long getSunrise() {
            return sunrise;
        }

        public void setSunrise(Long sunrise) {
            this.sunrise = sunrise;
        }

        public Long getSunset() {
            return sunset;
        }

        public void setSunset(Long sunset) {
            this.sunset = sunset;
        }
    }

    public static class Coordinates {
        private Double lon;
        private Double lat;

        // Getters and Setters

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLon() {
            return lon;
        }

        public void setLon(Double lon) {
            this.lon = lon;
        }
    }
}
