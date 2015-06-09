package nl.teamone.projectholiday.api;

import java.util.Date;

import nl.teamone.projectholiday.api.objects.Location;
import nl.teamone.projectholiday.api.objects.PredictionType;
import nl.teamone.projectholiday.api.objects.WeatherDay;
import nl.teamone.projectholiday.api.objects.WeatherPeriod;
import rx.Observable;

public class StubApi extends DataRetriever {

    public static Observable<WeatherPeriod> getWeatherData(Location loc, Date from, Date to) {
        WeatherPeriod weatherPeriod = new WeatherPeriod(from, to);
        weatherPeriod.bestPredictionType = getBestPredictionType(from, to);
        // TODO: Add more stub data
        return Observable.just(weatherPeriod);
    }

    public static PredictionType getBestPredictionType(Date from, Date to) {
        return PredictionType.CLIMATE;
    }

}
