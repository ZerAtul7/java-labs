package com.kroll;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONObject;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

    public class Main {

        private static final String API_KEY = "63f3fdeafb600a5001de0dea354f6c6d";

        public static void main(String[] args) {
            String[] cities = {"Kyiv", "Lviv", "Odesa", "Kharkiv", "Dnipro", "Zaporizhia", "Donetsk", "Luhansk", "Kherson", "Mykolaiv"};

            System.out.println("| Місто      | Найгарячіші 10 днів | Найхолодніші 10 днів | Найвологіші 10 днів | Дні з опадами | Зростання темп. |");
            System.out.println("|------------|---------------------|-----------------------|----------------------|---------------|-----------------|");

            for (String city : cities) {
                List<WeatherData> weatherDataList = getWeatherData(city);

                if (!weatherDataList.isEmpty()) {
                    analyzeExtremeWeather(weatherDataList, city);
                }
            }

            System.out.println("\n| Місяць      | Середня темп. | Середня вологість | Середні опади | Найвища шв. вітру |");
            System.out.println("|-------------|-----------------|---------------------|----------------|--------------------|");

            for (String city : cities) {
                List<WeatherData> weatherDataList = getWeatherData(city);

                if (!weatherDataList.isEmpty()) {
                    aggregateStatistics(weatherDataList, city);
                }
            }
        }

        private static void analyzeExtremeWeather(List<WeatherData> weatherDataList, String city) {
            List<WeatherData> hottestDays = weatherDataList.stream()
                    .sorted(Comparator.comparingDouble(WeatherData::getTemperature).reversed())
                    .limit(10)
                    .collect(Collectors.toList());

            List<WeatherData> coldestDays = weatherDataList.stream()
                    .sorted(Comparator.comparingDouble(WeatherData::getTemperature))
                    .limit(10)
                    .collect(Collectors.toList());

            List<WeatherData> mostHumidDays = weatherDataList.stream()
                    .sorted(Comparator.comparingInt(WeatherData::getHumidity).reversed())
                    .limit(10)
                    .collect(Collectors.toList());

            List<WeatherData> rainyDays = findConsecutiveRainyDays(weatherDataList);
            List<WeatherData> temperatureRiseDays = findTemperatureRiseDays(weatherDataList);

            if (!hottestDays.isEmpty() || !coldestDays.isEmpty() || !mostHumidDays.isEmpty() || !rainyDays.isEmpty() || !temperatureRiseDays.isEmpty()) {
                System.out.printf("| %-10s | %-19s | %-21s | %-20s | %-13s | %-15s |\n", city,
                        formatWeatherDataList(hottestDays),
                        formatWeatherDataList(coldestDays),
                        formatWeatherDataList(mostHumidDays),
                        formatWeatherDataList(rainyDays),
                        formatWeatherDataList(temperatureRiseDays));
            }
        }

        private static void aggregateStatistics(List<WeatherData> weatherDataList, String city) {
            Map<Integer, Double> monthlyAverageTemperature = calculateMonthlyAverage(weatherDataList, WeatherData::getTemperature);
            Map<Integer, Double> monthlyAverageHumidity = calculateMonthlyAverage(weatherDataList, WeatherData::getHumidity);
            Map<Integer, Double> monthlyAveragePrecipitation = calculateMonthlyAverage(weatherDataList, WeatherData::getPrecipitation);
            Map<Integer, Double> monthlyAverageWindSpeed = calculateMonthlyAverage(weatherDataList, WeatherData::getWindSpeed);

            int monthWithHighestWindSpeed = Collections.max(monthlyAverageWindSpeed.entrySet(), Map.Entry.comparingByValue()).getKey();

            if (!monthlyAverageTemperature.isEmpty() || !monthlyAverageHumidity.isEmpty() || !monthlyAveragePrecipitation.isEmpty() || !monthlyAverageWindSpeed.isEmpty()) {
                System.out.printf("| %-11s | %-15s | %-18s | %-14s | %-18s |\n", city,
                        formatMonthlyStatistics(monthlyAverageTemperature),
                        formatMonthlyStatistics(monthlyAverageHumidity),
                        formatMonthlyStatistics(monthlyAveragePrecipitation),
                        monthWithHighestWindSpeed);
            }
        }


        private static List<WeatherData> findConsecutiveRainyDays(List<WeatherData> weatherDataList) {
            List<WeatherData> consecutiveRainyDays = new ArrayList<>();
            int consecutiveRainyCount = 0;

            for (WeatherData weatherData : weatherDataList) {
                if (weatherData.getPrecipitation() > 0) {
                    consecutiveRainyCount++;
                    if (consecutiveRainyCount > 7) {
                        consecutiveRainyDays.add(weatherData);
                    }
                } else {
                    consecutiveRainyCount = 0;
                }
            }

            return consecutiveRainyDays;
        }

        private static List<WeatherData> findTemperatureRiseDays(List<WeatherData> weatherDataList) {
            List<WeatherData> temperatureRiseDays = new ArrayList<>();
            int consecutiveTemperatureRiseCount = 0;

            for (int i = 0; i < weatherDataList.size() - 1; i++) {
                WeatherData currentDay = weatherDataList.get(i);
                WeatherData nextDay = weatherDataList.get(i + 1);

                if (nextDay.getTemperature() - currentDay.getTemperature() >= 5) {
                    consecutiveTemperatureRiseCount++;
                    if (consecutiveTemperatureRiseCount >= 5) {
                        temperatureRiseDays.add(currentDay);
                        temperatureRiseDays.add(nextDay);
                    }
                } else {
                    consecutiveTemperatureRiseCount = 0;
                }
            }

            return temperatureRiseDays;
        }

        private static <T> Map<Integer, Double> calculateMonthlyAverage(List<WeatherData> weatherDataList, ValueExtractor<T> valueExtractor) {
            Map<Integer, List<T>> monthlyData = new HashMap<>();

            for (WeatherData weatherData : weatherDataList) {
                int month = LocalDate.parse(weatherData.getDate()).getMonthValue();
                monthlyData.computeIfAbsent(month, k -> new ArrayList<>()).add(valueExtractor.extract(weatherData));
            }

            return monthlyData.entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, entry ->
                            entry.getValue().stream().mapToDouble(valueExtractor::toDouble).average().orElse(0)));
        }

        private static String formatWeatherDataList(List<WeatherData> weatherDataList) {
            return weatherDataList.stream()
                    .map(weatherData -> weatherData.getDate() + ": " + weatherData.getTemperature() + "°C")
                    .collect(Collectors.joining(", "));
        }

        private static String formatMonthlyStatistics(Map<Integer, Double> monthlyStatistics) {
            return monthlyStatistics.entrySet().stream()
                    .map(entry -> "Month " + entry.getKey() + ": " + String.format("%.2f", entry.getValue()))
                    .collect(Collectors.joining(", "));
        }

        private static List<WeatherData> getWeatherData(String city) {
            List<WeatherData> weatherDataList = new ArrayList<>();

            try {
                String apiUrl = String.format("https://api.openweathermap.org/data/2.5/forecast?q=%s&appid=%s", city, API_KEY);
                URL url = new URL(apiUrl);
                URLConnection connection = url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                JSONObject jsonResponse = new JSONObject(response.toString());

                if (jsonResponse.has("list")) {
                    jsonResponse.getJSONArray("list").forEach(item -> {
                        JSONObject data = (JSONObject) item;
                        String date = data.getString("dt_txt").split(" ")[0];
                        double temperature = data.getJSONObject("main").getDouble("temp") - 273.15;
                        int humidity = data.getJSONObject("main").getInt("humidity");
                        double precipitation = 0;
                        if (data.has("rain")) {
                            precipitation += data.getJSONObject("rain").getDouble("3h");
                        }
                        if (data.has("snow")) {
                            precipitation += data.getJSONObject("snow").getDouble("3h");
                        }

                        double windSpeed = data.getJSONObject("wind").getDouble("speed");

                        WeatherData weatherData = new WeatherData(date, temperature, humidity, precipitation, windSpeed);
                        weatherDataList.add(weatherData);
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return weatherDataList;
        }

        interface ValueExtractor<T> {
            T extract(WeatherData weatherData);

            default double toDouble(T value) {
                if (value instanceof Number) {
                    return ((Number) value).doubleValue();
                }
                return 0;
            }
        }
    }

