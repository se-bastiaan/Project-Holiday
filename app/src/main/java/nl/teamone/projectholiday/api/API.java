package nl.teamone.projectholiday.api;

import java.util.Date;

import nl.teamone.projectholiday.api.objects.Location;
import nl.teamone.projectholiday.api.objects.PredictionType;
import nl.teamone.projectholiday.api.objects.WeatherDay;
import nl.teamone.projectholiday.api.objects.WeatherPeriod;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Class that determines which API will be used and forwards the request to the appropriate one
 */
public class API extends DataRetriever {

    public static Observable<WeatherPeriod> getWeatherData(Location loc, Date from, Date to) {
        return OpenWeatherMapApi.getWeatherData(loc, from, to);
    }

    public static WeatherDay getCurrentWeather(Location loc) {
        return OpenWeatherMapApi.getCurrentWeather(loc);
    }

    public static PredictionType getBestPredictionType(Date from, Date to) {
        return OpenWeatherMapApi.getBestPredictionType(from, to);
    }

    public static Boolean canMakeConnectionWithApi() {
        return OpenWeatherMapApi.canMakeConnectionWithApi();
    }

}
