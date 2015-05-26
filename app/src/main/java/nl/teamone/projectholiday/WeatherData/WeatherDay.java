package nl.teamone.projectholiday.WeatherData;

import java.util.Date;

public class WeatherDay {

    public final Date day;
    public final Location location;
    public final PredictionType predictionType;

    // Temperature is given in Celsius, rounded down to the closest integer.
    public int temperatureHigh;
    public int temperatureMean;
    public int temperatureLow;
    public int temperatureFeelsLike;

    // Wind speed is given in KM/H
    public int windSpeed;

    public Date timeSunrise;
    public Date timeSunset;

    // Rain (Or snow, depending on temperature) is given in millimeters per day.
    // Chance is 0-100%
    public int rainAmountinMillimeter;
    public int rainPercentChance;

    public WeatherDay(Date _day,
                      Location _location,
                      PredictionType _predictionType) {
        this.day = _day;
        this.location = _location;
        this.predictionType = _predictionType;
    }

}
