package com.kroll;

class WeatherData {
    private String date;
    private double temperature;
    private int humidity;
    private double precipitation;
    private double windSpeed;

    public WeatherData(String date, double temperature, int humidity, double precipitation, double windSpeed) {
        this.date = date;
        this.temperature = temperature;
        this.humidity = humidity;
        this.precipitation = precipitation;
        this.windSpeed = windSpeed;
    }

    public String getDate() {
        return date;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public double getWindSpeed() {
        return windSpeed;
    }
}