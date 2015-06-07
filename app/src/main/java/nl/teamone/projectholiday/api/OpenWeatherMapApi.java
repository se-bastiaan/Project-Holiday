package nl.teamone.projectholiday.api;

import java.util.Date;

import nl.teamone.projectholiday.api.objects.Location;
import nl.teamone.projectholiday.api.objects.PredictionType;
import nl.teamone.projectholiday.api.objects.WeatherDay;
import nl.teamone.projectholiday.api.objects.WeatherPeriod;
import nl.teamone.projectholiday.api.responses.openweathermap.Response;
import nl.teamone.projectholiday.api.services.OpenWeatherMapService;
import retrofit.RestAdapter;
import rx.Observable;
import rx.functions.Func1;

public class OpenWeatherMapApi extends DataRetriever {

    public static final String requiredMode = "json";
    public static final String baseURL = "http://api.openweathermap.org";
    public static final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;

    public static Observable<WeatherPeriod> getWeatherData(Location loc, Date from, Date to) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(baseURL)
                .build();

        OpenWeatherMapService apiRetriever = restAdapter.create(OpenWeatherMapService.class);

        return apiRetriever.getWeatherForeCast(
                getQueryLocation(loc),
                Integer.toString(getDuration(from, to)),
                requiredMode)
                .map(new Func1<Response, WeatherPeriod>() {
                    @Override
                    public WeatherPeriod call(Response response) {
                        return processResponse(response);
                    }
                });
    }

    public static WeatherDay getCurrentWeather(Location loc) {
        return null;
    }

    public static PredictionType getBestPredictionType(Date from, Date to) {
        return null;
    }

    private static String getQueryLocation(Location location) {
        return location.city.toLowerCase() + "," + location.countryISO.toLowerCase();
    }

    private static Integer getDuration(Date from, Date to) {
        return 1 + ((int) ((from.getTime() - to.getTime()) / DAY_IN_MILLIS));
    }

    private static WeatherPeriod processResponse(Response response) {
        return null;
    }
}
