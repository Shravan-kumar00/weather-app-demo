package com.weather;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q={city}&appid={apiKey}&units=metric";

    public WeatherResponse getWeather(String city) {
        String apiKey = System.getenv("OPENWEATHER_API_KEY");  // Set the API key as an environment variable
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(API_URL, WeatherResponse.class, city, apiKey);
    }
}
