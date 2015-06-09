package nl.teamone.projectholiday.api.objects;


import java.util.ArrayList;
import java.util.Date;

public class WeatherPeriod {

    private static final int TEMPERATURELOW = 0;
    private static final int TEMPERATUREMID = 10;
    private static final int TEMPERATUREHIGH = 20;

    private static final int RAINCHANCELOW = 25;
    private static final int RAINCHANCEMID = 50;
    private static final int RAINCHANCEHIGH = 75;

    public final Date startDay;
    public final Date endDay;
    public PredictionType bestPredictionType;
    public int totalDaysRainChanceNone;         // < LOW
    public int totalDaysRainChanceLow;          // >= LOW && < MID
    public int totalDaysRainChanceMid;          // >= MID && < HIGH
    public int totalDaysRainChanceHigh;         // > HIGH
    // Uses the temperatureMean attribute
    public int totalDaysTemperatureLow;         // < LOW
    public int totalDaysTemperatureMid;         // >= LOW && < MID
    public int totalDaysTemperatureHigh;        // >= MID && < HIGH
    public int totalDaysTemperatureTop;         // > HIGH
    private ArrayList<WeatherDay> weatherData;

    /**
     * Constructor for WeatherPeriod.
     * Sets values to params, then makes weatherData a new ArrayList
     * and sets all daycounters to 0.
     * @param start
     * @param end
     */
    public WeatherPeriod(Date start, Date end) {
        startDay = start;
        endDay = end;
        weatherData = new ArrayList<>();
        resetTotalDays();
    }

    /**
     * Combines two WeatherPeriods into a new WeatherPeriod.
     * Does not do any checking to see whether the periods match up.
     * For proper usage, the first day of the second WeatherPeriod should be
     * one day past the last day of the first WeatherPeriod.
     *
     * @param first  The first WeatherPeriod.
     * @param second The second WeatherPeriod.
     * @return a new instance of a WeatherPeriod containing both.
     */
    public static WeatherPeriod appendPeriod(WeatherPeriod first, WeatherPeriod second) {
        WeatherPeriod response = new WeatherPeriod(first.startDay, second.endDay);
        response.weatherData = first.weatherData;
        response.weatherData.addAll(second.weatherData);
        response.calculateTotalDays();
        return response;
    }

    /**
     * Sets all daycounters to 0 and the predictionType to NODATA
     */
    private void resetTotalDays() {
        totalDaysRainChanceNone = totalDaysRainChanceLow = totalDaysRainChanceMid = totalDaysRainChanceHigh =
                totalDaysTemperatureLow = totalDaysTemperatureMid = totalDaysTemperatureHigh = totalDaysTemperatureTop = 0;
        bestPredictionType = PredictionType.NODATA;
    }

    /**
     * Getter for weatherData
     * @return weatherData
     */
    public ArrayList<WeatherDay> getWeatherData() {
        return weatherData;
    }

    /**
     * Adds WeatherDay to weatherData
     * @param day
     */
    public void addWeatherDay(WeatherDay day) {
        this.weatherData.add(day);
    }

    /**
     * First sets all daycounters to 0, then calculates them again
     */
    public void calculateTotalDays() {
        resetTotalDays();
        for (WeatherDay day : weatherData) {

            // Update total rain days.
            if (day.getRainPercentChance() >= RAINCHANCELOW) {
                if (day.getRainPercentChance() >= RAINCHANCEMID) {
                    if (day.getRainPercentChance() >= RAINCHANCEHIGH) {
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
            if (day.getTemperatureMean() < TEMPERATUREHIGH) {
                if (day.getTemperatureMean() < TEMPERATUREMID) {
                    if (day.getTemperatureMean() < TEMPERATURELOW) {
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

            if (bestPredictionType.ordinal() > day.getPredictionType().ordinal()) {
                bestPredictionType = day.getPredictionType();
            }
        }
    }

}
