package nl.teamone.projectholiday.WeatherData;

import java.util.Date;

public class ApiOpenWeatherMap implements IDataRetriever {
    private static final String apiKey = "dce718f5c13cf6686383c372a35bdf7b";

    @Override
    public WeatherPeriod getWeatherData(Location loc, Date from, Date to) {
        return null;
    }

    @Override
    public WeatherDay getCurrentWeather(Location loc) {
        return null;
    }

    @Override
    public PredictionType getBestPredictionType(Date from, Date to) {
        return null;
    }

    @Override
    public Boolean canMakeConnectionWithApi() {
        return null;
    }
}
