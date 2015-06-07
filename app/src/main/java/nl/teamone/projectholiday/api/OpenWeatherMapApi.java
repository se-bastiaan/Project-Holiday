package nl.teamone.projectholiday.api;

import java.util.Date;

import nl.teamone.projectholiday.api.objects.Location;
import nl.teamone.projectholiday.api.objects.PredictionType;
import nl.teamone.projectholiday.api.objects.WeatherDay;
import nl.teamone.projectholiday.api.objects.WeatherPeriod;
import nl.teamone.projectholiday.api.services.OpenWeatherMapService;
import nl.teamone.projectholiday.api.responses.openweathermap.Response;
import retrofit.RestAdapter;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class OpenWeatherMapApi extends DataRetriever {

    public static final String requiredMode = "json";
    public static final String baseURL = "http://api.openweathermap.org";
    public static final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;

    public static Subscription getWeatherData(Location loc, Date from, Date to, Action1<WeatherPeriod> subscriber) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(baseURL)
                .build();

        OpenWeatherMapService apiRetriever = restAdapter.create(OpenWeatherMapService.class);

        return apiRetriever.getWeatherForeCast(
                getQueryLocation(loc),
                Integer.toString(getDuration(from, to)),
                requiredMode)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<Response, WeatherPeriod>() {
                    @Override
                    public WeatherPeriod call(Response response) {
                        return processResponse(response);
                    }
                }).subscribe(subscriber);
    }

    public static WeatherDay getCurrentWeather(Location loc) {
        return null;
    }

    public static PredictionType getBestPredictionType(Date from, Date to) {
        return null;
    }

    public static Boolean canMakeConnectionWithApi() {
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
