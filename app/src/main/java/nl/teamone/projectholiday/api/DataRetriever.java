package nl.teamone.projectholiday.api;

import java.util.Date;

import nl.teamone.projectholiday.api.objects.Location;
import nl.teamone.projectholiday.api.objects.PredictionType;
import nl.teamone.projectholiday.api.objects.WeatherDay;
import nl.teamone.projectholiday.api.objects.WeatherPeriod;
import nl.teamone.projectholiday.api.responses.openweathermap.Response;
import rx.Observable;
import rx.functions.Action1;

public abstract class DataRetriever {

    public static final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;

    /**
     *
     * @param loc
     * @param from
     * @param to
     * @param subscriber
     * @return
     */
    public static Observable<WeatherPeriod> getWeatherData(Location loc, Date from, Date to, Action1<WeatherPeriod> subscriber) {
        throw new NoSuchMethodError();
    }

    /**
     *
     * @param from
     * @param to
     * @return
     */
    public static PredictionType getBestPredictionType(Date from, Date to) {
        throw new NoSuchMethodError();
    }

    public static Observable<Response> getCurrentWeather(Location loc, Action1<WeatherDay> subscriber) {
        throw new NoSuchMethodError();
    }

    /**
     * Calculates total number of days of vacation
     * @param from
     * @param to
     * @return total days
     */
    public static Integer getDuration(Date from, Date to) {
        return 1 + ((int) ((to.getTime() - from.getTime()) / DAY_IN_MILLIS));
    }

}
