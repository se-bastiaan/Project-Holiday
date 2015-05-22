package nl.teamone.projectholiday.WeatherData;


import java.util.ArrayList;
import java.util.Date;

public class WeatherPeriod {

    public final Date startDay;
    public final Date endDay;

    private ArrayList<WeatherDay> weatherData;

    public int totalDaysRainChanceOver25;
    public int totalDaysRainChanceOver50;
    public int totalDaysRainChanceOver75;

    // Uses the temperatureFeelsLike attribute
    public int totalDaysTemperatureUnder0;
    public int totalDaysTemperatureUnder10;
    public int totalDaysTemperatureUnder20;

    public WeatherPeriod(Date _start, Date _end) {
        this.startDay = _start;
        this.endDay = _end;
        this.weatherData = new ArrayList<>();
    }

    public ArrayList<WeatherDay> getWeatherData() {
        return weatherData;
    }

    public void addWeatherDay(WeatherDay _day) {
        this.weatherData.add(_day);
    }

    public void calculateTotalDays() {
        // Todo: Calculate the total amounts in this period.
    }

}
