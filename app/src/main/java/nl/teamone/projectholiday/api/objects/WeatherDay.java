package nl.teamone.projectholiday.api.objects;

import java.util.Date;

public class WeatherDay {

    private final Date mDay;
    private final Location mLocation;
    private final PredictionType mPredictionType;
    private Date mTimeSunrise;
    private Date mTimeSunset;
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

    public PredictionType getPredictionType() { return mPredictionType;}

    public int getRainPerc() {
        return mRainPercentChance;
    }

    public int getRainMM() {
        return mRainAmountinMillimeter;
    }

    public int getTempHigh() {
        return mTemperatureHigh;
    }

    public int getTempMean() {
        return mTemperatureMean;
    }

    public int getTempLow() {
        return mTemperatureLow;
    }

    public int getTempFeel() {
        return mTemperatureFeelsLike;
    }


}
