package nl.teamone.projectholiday.WeatherData;

import java.util.Date;

public class WeatherDay {

    public final Date day;
    public final Location location;
    public final PredictionType predictionType;

    // Temperature is given in Celsius, rounded down to the closest integer.
    public int mTemperatureHigh;
    public int mTemperatureMean;
    public int mTemperatureLow;
    public int mTemperatureFeelsLike;

    // Wind speed is given in KM/H
    public int mWindSpeed;

    public Date timeSunrise;
    public Date timeSunset;

    // Rain (Or snow, depending on temperature) is given in millimeters per day.
    // Chance is 0-100%
    public int mRainAmountinMillimeter;
    public int mRainPercentChance;

    public WeatherDay(Date _day,
                      Location _location,
                      PredictionType _predictionType) {
        this.day = _day;
        this.location = _location;
        this.predictionType = _predictionType;
    }
    public int getRainPerc(){
        return mRainPercentChance;
    }
    public int getRainMM(){
        return mRainAmountinMillimeter;
    }
    public int getTempHigh(){
        return mTemperatureHigh;
    }
    public int getTempMean(){
        return mTemperatureMean;
    }
    public int getTempLow() {return mTemperatureLow; }
    public int getTempFeel(){ return mTemperatureFeelsLike; }



}
