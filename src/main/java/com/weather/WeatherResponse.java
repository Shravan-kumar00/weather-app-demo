package com.weather;

public class WeatherResponse {
    private String name;
    private Main main;
    private String description;

    // Getters and Setters

    public static class Main {
        private double temp;
        private int humidity;

        // Getters and Setters
    }
}
