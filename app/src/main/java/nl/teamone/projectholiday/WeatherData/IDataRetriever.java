package nl.teamone.projectholiday.WeatherData;

import java.util.Date;

public interface IDataRetriever {

    WeatherPeriod getWeatherData(Location loc, Date from, Date to);
    WeatherDay getCurrentWeather(Location loc);

}
