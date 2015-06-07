package nl.teamone.projectholiday.api;

import java.util.Date;

import nl.teamone.projectholiday.api.responses.openweathermap.IOpenWeatherMap;
import nl.teamone.projectholiday.api.responses.openweathermap.Response;
import retrofit.RestAdapter;

public class ApiOpenWeatherMap implements IDataRetriever {

    public static final String requiredMode = "json";
    public static final String baseURL = "http://api.openweathermap.org";
    public static final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;

    @Override
    public WeatherPeriod getWeatherData(Location loc, Date from, Date to) {

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(baseURL)
                .build();

        IOpenWeatherMap apiRetriever = restAdapter.create(IOpenWeatherMap.class);

        Response response = apiRetriever.getWeatherForeCast(
                getQueryLocation(loc),
                Integer.toString(getDuration(from, to)),
                requiredMode);

        return processResponse(response);

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

    private String getQueryLocation(Location location) {
        return location.city.toLowerCase() + "," + location.countryISO.toLowerCase();
    }

    private Integer getDuration(Date from, Date to) {
        return 1 + ((int) ((from.getTime() - to.getTime()) / DAY_IN_MILLIS));
    }

    private WeatherPeriod processResponse(Response response) {
        return null;
    }
}
