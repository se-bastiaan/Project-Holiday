package nl.teamone.projectholiday.api;

import java.util.Date;

import nl.teamone.projectholiday.api.objects.Location;
import nl.teamone.projectholiday.api.objects.PredictionType;
import nl.teamone.projectholiday.api.objects.WeatherDay;
import nl.teamone.projectholiday.api.responses.openweathermap.Response;
import rx.Subscription;
import rx.exceptions.OnErrorNotImplementedException;
import rx.functions.Action1;

public abstract class DataRetriever {

    public static Subscription getWeatherData(Location loc, Date from, Date to, Action1<Response> subscriber) {
        throw new NoSuchMethodError();
    }

    public static WeatherDay getCurrentWeather(Location loc) {
        throw new NoSuchMethodError();
    }

    public static PredictionType getBestPredictionType(Date from, Date to) {
        throw new NoSuchMethodError();
    }

    public static Boolean canMakeConnectionWithApi() {
        throw new NoSuchMethodError();
    }

}
