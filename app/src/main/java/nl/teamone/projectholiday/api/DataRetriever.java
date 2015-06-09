package nl.teamone.projectholiday.api;

import java.util.Date;

import nl.teamone.projectholiday.api.objects.Location;
import nl.teamone.projectholiday.api.objects.PredictionType;
import nl.teamone.projectholiday.api.objects.WeatherPeriod;
import rx.Observable;
import rx.functions.Action1;

public abstract class DataRetriever {

    public static final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;

    public static Observable<WeatherPeriod> getWeatherData(Location loc, Date from, Date to, Action1<WeatherPeriod> subscriber) {
        throw new NoSuchMethodError();
    }

    public static PredictionType getBestPredictionType(Date from, Date to) {
        throw new NoSuchMethodError();
    }

    public static Integer getDuration(Date from, Date to) {
        return 1 + ((int) ((from.getTime() - to.getTime()) / DAY_IN_MILLIS));
    }

}
