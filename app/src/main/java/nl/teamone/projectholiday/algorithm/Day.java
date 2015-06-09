package nl.teamone.projectholiday.algorithm;

import nl.teamone.projectholiday.api.objects.WeatherDay;

public class Day {
    private Calculation mCalculation;
    private WeatherDay mWeatherDay;

    /**
     * Constructor, which immediately sets day type
     * @param wDay
     */
    public Day(WeatherDay wDay) {
        this.mCalculation = new Calculation(wDay);
        this.mWeatherDay = wDay;
    }

    /**
     * Getter for the day type
     * @return {@link DayType}
     */
    public DayType getDay() {
        return mCalculation.getDay();
    }

    /**
     * Getter for the night type
     * @return {@link NightType}
     */
    public NightType getNight() {
        return mCalculation.getNight();
    }

    /**
     * Getter for the weather day
     * @return mWeatherDay
     */
    public WeatherDay getWeatherDay() {
        return mWeatherDay;
    }
}
