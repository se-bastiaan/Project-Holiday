package nl.teamone.projectholiday.api.objects;

import java.util.Date;

public class WeatherDay {

    private final Date mDay;
    private final Location mLocation;
    private final PredictionType mPredictionType;
    // Temperature is given in Celsius, rounded down to the closest integer.
    private int mTemperatureHigh;
    private int mTemperatureMean;
    private int mTemperatureLow;
    private int mTemperatureFeelsLike;
    // Wind speed is given in KM/H
    private int mWindSpeed;
    // Rain (Or snow, depending on temperature) is given in millimeters per mDay.
    // Chance is 0-100%
    private int mRainAmountinMillimeter;
    private int mRainPercentChance;

    public WeatherDay(Date day, Location location, PredictionType predictionType) {
        mDay = day;
        mLocation = location;
        mPredictionType = predictionType;
    }

    public void setTemperatureHigh(int temperatureHigh) {
        mTemperatureHigh = temperatureHigh;
    }

    public void setTemperatureMean(int temperatureMean) {
        mTemperatureMean = temperatureMean;
    }

    public void setTemperatureLow(int temperatureLow) {
        mTemperatureLow = temperatureLow;
    }

    public void setTemperatureFeelsLike(int temperatureFeelsLike) {
        mTemperatureFeelsLike = temperatureFeelsLike;
    }

    public void setWindSpeed(int windSpeed) {
        mWindSpeed = windSpeed;
    }

    public void setRainAmountInMillimeter(int tainAmountinMillimeter) {
        mRainAmountinMillimeter = tainAmountinMillimeter;
    }

    public void setRainPercentChance(int rainPercentChance) {
        mRainPercentChance = rainPercentChance;
    }

    public PredictionType getPredictionType() {
        return mPredictionType;
    }

    public int getRainPercentChance() {
        return mRainPercentChance;
    }

    public int getRainAmountInMillimeter() {
        return mRainAmountinMillimeter;
    }

    public int getTemperatureHigh() {
        return mTemperatureHigh;
    }

    public int getTemperatureMean() {
        return mTemperatureMean;
    }

    public int getTemperatureLow() {
        return mTemperatureLow;
    }

    public int getTemperatureFeel() {
        return mTemperatureFeelsLike;
    }

    public int getWindSpeed() {
        return mWindSpeed;
    }


}
