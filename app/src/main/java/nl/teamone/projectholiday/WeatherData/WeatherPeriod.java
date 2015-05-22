package nl.teamone.projectholiday.WeatherData;


import java.util.ArrayList;
import java.util.Date;

public class WeatherPeriod {

    private static final int TEMPERATURELOW =   0;
    private static final int TEMPERATUREMID =   10;
    private static final int TEMPERATUREHIGH =  20;

    private static final int RAINCHANCELOW =    25;
    private static final int RAINCHANCEMID =    50;
    private static final int RAINCHANCEHIGH =   75;

    public final Date startDay;
    public final Date endDay;

    private ArrayList<WeatherDay> weatherData;

    public PredictionType bestPredictionType;

    public int totalDaysRainChanceLow;          // >= LOW && < MID
    public int totalDaysRainChanceMid;          // >= MID && < HIGH
    public int totalDaysRainChanceHigh;         // > HIGH

    // Uses the temperatureFeelsLike attribute
    public int totalDaysTemperatureLow;         // < LOW
    public int totalDaysTemperatureMid;         // >= LOW && < MID
    public int totalDaysTemperatureHigh;        // >= MID && < HIGH

    public WeatherPeriod(Date _start, Date _end) {
        this.startDay = _start;
        this.endDay = _end;
        this.weatherData = new ArrayList<>();
        resetTotalDays();
    }

    private void resetTotalDays() {
        totalDaysRainChanceLow  = totalDaysRainChanceMid  = totalDaysRainChanceHigh  =
        totalDaysTemperatureLow = totalDaysTemperatureMid = totalDaysTemperatureHigh = 0;
        bestPredictionType = PredictionType.NODATA;
    }

    public ArrayList<WeatherDay> getWeatherData() {
        return weatherData;
    }

    public void addWeatherDay(WeatherDay _day) {
        this.weatherData.add(_day);
    }

    public void calculateTotalDays() {
        resetTotalDays();
        for (WeatherDay day : weatherData) {
            // Todo: Do the checks here
        }
    }

}
