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

    public int totalDaysRainChanceNone;         // < LOW
    public int totalDaysRainChanceLow;          // >= LOW && < MID
    public int totalDaysRainChanceMid;          // >= MID && < HIGH
    public int totalDaysRainChanceHigh;         // > HIGH

    // Uses the temperatureFeelsLike attribute
    public int totalDaysTemperatureLow;         // < LOW
    public int totalDaysTemperatureMid;         // >= LOW && < MID
    public int totalDaysTemperatureHigh;        // >= MID && < HIGH
    public int totalDaysTemperatureTop;         // > HIGH

    /**
     * Combines two weatherperiods into a new weatherperiod.
     * Does not do any checking to see whether the periods match up.
     * For proper usage, the first day of the second weatherperiod should be
     * one day past the last day of the first weatherperiod.
     * @param first The first weatherperiod.
     * @param second The second weatherperiod.
     * @return a new instance of a weatherperiod containing both.
     */
    public static WeatherPeriod appendPeriod(WeatherPeriod first, WeatherPeriod second) {
        WeatherPeriod response = new WeatherPeriod(first.startDay, second.endDay);
        response.weatherData = first.weatherData;
        response.weatherData.addAll(second.weatherData);
        response.calculateTotalDays();
        return response;
    }

    public WeatherPeriod(Date _start, Date _end) {
        this.startDay = _start;
        this.endDay = _end;
        this.weatherData = new ArrayList<>();
        resetTotalDays();
    }

    private void resetTotalDays() {
        totalDaysRainChanceNone = totalDaysRainChanceLow  = totalDaysRainChanceMid   = totalDaysRainChanceHigh =
        totalDaysTemperatureLow = totalDaysTemperatureMid = totalDaysTemperatureHigh = totalDaysTemperatureTop = 0;
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

            // Update total rain days.
            if (day.mRainPercentChance >= RAINCHANCELOW) {
                if (day.mRainPercentChance >= RAINCHANCEMID) {
                    if (day.mRainPercentChance >= RAINCHANCEHIGH) {
                        totalDaysRainChanceHigh++;
                    } else {
                        totalDaysRainChanceMid++;
                    }
                } else {
                    totalDaysRainChanceLow++;
                }
            } else {
                totalDaysRainChanceNone++;
            }

            // Update total temperature days.
            if (day.mTemperatureFeelsLike < TEMPERATUREHIGH) {
                if (day.mTemperatureFeelsLike < TEMPERATUREMID) {
                    if (day.mTemperatureFeelsLike < TEMPERATURELOW) {
                        totalDaysTemperatureLow++;
                    } else {
                        totalDaysTemperatureMid++;
                    }
                } else {
                    totalDaysTemperatureHigh++;
                }
            } else {
                totalDaysTemperatureTop++;
            }

            if (bestPredictionType.ordinal() > day.predictionType.ordinal()) {
                bestPredictionType = day.predictionType;
            }
        }
    }

}
