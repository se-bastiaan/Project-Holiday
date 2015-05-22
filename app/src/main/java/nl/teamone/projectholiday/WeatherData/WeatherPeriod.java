package nl.teamone.projectholiday.WeatherData;


import java.util.ArrayList;
import java.util.Date;

public class WeatherPeriod {

    public final Date startDay;
    public final Date endDay;

    private ArrayList<WeatherDay> weatherData;

    public int totalDaysRainChanceBetween25And50;   // >= 25 && < 50
    public int totalDaysRainChanceBetween50And75;   // >= 50 && < 75
    public int totalDaysRainChanceOver75;           // > 75

    // Uses the temperatureFeelsLike attribute
    public int totalDaysTemperatureUnder0;          // < 0
    public int totalDaysTemperatureBetween0And10;   // >= 0 && < 10
    public int totalDaysTemperatureBetween10And20;  // >= 10 && < 20

    public WeatherPeriod(Date _start, Date _end) {
        this.startDay = _start;
        this.endDay = _end;
        this.weatherData = new ArrayList<>();
        resetTotalDays();
    }

    public ArrayList<WeatherDay> getWeatherData() {
        return weatherData;
    }

    public void addWeatherDay(WeatherDay _day) {
        this.weatherData.add(_day);
    }

    private void resetTotalDays() {
        totalDaysRainChanceBetween25And50 = 0;
        totalDaysRainChanceBetween50And75 = 0;
        totalDaysRainChanceOver75 = 0;
        totalDaysTemperatureUnder0 = 0;
        totalDaysTemperatureBetween0And10 = 0;
        totalDaysTemperatureBetween10And20 = 0;
    }

    public void calculateTotalDays() {
        resetTotalDays();
        for (WeatherDay day : weatherData) {
            // Todo: Do the checks here.
        }
    }

}
