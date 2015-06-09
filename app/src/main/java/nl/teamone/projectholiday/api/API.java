package nl.teamone.projectholiday.api;

import java.util.Date;

import nl.teamone.projectholiday.api.objects.Location;
import nl.teamone.projectholiday.api.objects.PredictionType;
import nl.teamone.projectholiday.api.objects.WeatherDay;
import nl.teamone.projectholiday.api.objects.WeatherPeriod;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

/**
 * Class that determines which API will be used and forwards the request to the appropriate one
 */
public class API extends DataRetriever {

    public static Observable<WeatherPeriod> getWeatherData(Location loc, Date from, Date to) {
        Date now = new Date();

        // Check if we can use the Forecast API entirely.
        if (OpenWeatherMapApi.getBestPredictionType(from, to) == PredictionType.FORECAST)
            return OpenWeatherMapApi.getWeatherData(loc, from, to);

        // If not, check if we can use the forecast API at all.
        if (OpenWeatherMapApi.getBestPredictionType(now, from) == PredictionType.NODATA)
            return WeatherUnderGroundApi.getWeatherData(loc, from, to);

        // We'll have to use a combination of both.
        Date checkDate = new Date();
        checkDate.setTime(to.getTime());
        while(OpenWeatherMapApi.getBestPredictionType(from, checkDate) == PredictionType.NODATA) {
            checkDate.setTime(checkDate.getTime() - DAY_IN_MILLIS);
        }
        Date newDate = new Date();
        newDate.setTime(checkDate.getTime() + DAY_IN_MILLIS);
        Observable<WeatherPeriod> secondObservable = WeatherUnderGroundApi.getWeatherData(loc, newDate, to);
        return OpenWeatherMapApi.getWeatherData(loc, from, checkDate).zipWith(secondObservable, new Func2<WeatherPeriod, WeatherPeriod, WeatherPeriod>() {
            @Override
            public WeatherPeriod call(WeatherPeriod weatherPeriod, WeatherPeriod weatherPeriod2) {
                return WeatherPeriod.appendPeriod(weatherPeriod, weatherPeriod2);
            }
        });

    }

    public static Observable<WeatherDay> getCurrentWeather (Location loc) {
        return OpenWeatherMapApi.getCurrentWeather(loc);
    }

    public static PredictionType getBestPredictionType(Date from, Date to) {
        if (OpenWeatherMapApi.getBestPredictionType(from, to) == PredictionType.FORECAST)
            return PredictionType.FORECAST;
        else
            return PredictionType.CLIMATE;
    }

}
