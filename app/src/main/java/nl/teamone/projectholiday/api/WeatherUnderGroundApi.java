package nl.teamone.projectholiday.api;

import java.util.Calendar;
import java.util.Date;

import nl.teamone.projectholiday.api.objects.Location;
import nl.teamone.projectholiday.api.objects.PredictionType;
import nl.teamone.projectholiday.api.objects.WeatherPeriod;
import nl.teamone.projectholiday.api.responses.weatherunderground.ApiResponse;
import nl.teamone.projectholiday.api.services.WeatherUnderGroundService;
import retrofit.RestAdapter;
import rx.Observable;
import rx.functions.Func1;

public class WeatherUnderGroundApi extends DataRetriever {

    public static final String baseURL = "http://api.wunderground.com";


    public static Observable<WeatherPeriod> getWeatherData(final Location loc, final Date from, final Date to) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(baseURL)
                .build();

        WeatherUnderGroundService apiRetriever = restAdapter.create(WeatherUnderGroundService.class);

        return apiRetriever.getClimateData(
                createDateString(from),
                createDateString(to),
                loc.countryISO,
                loc.city)
                .map(new Func1<ApiResponse, WeatherPeriod>() {
                    @Override
                    public WeatherPeriod call(ApiResponse response) {
                        return processResponse(response, from, to, loc);
                    }
                });
    }

    public static String createDateString(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String formattedMonth = String.format("%02d", cal.get(Calendar.MONTH));
        String formattedDay = String.format("%02d", cal.get(Calendar.DAY_OF_MONTH));
        return formattedMonth + formattedDay;
    }

    public static PredictionType getBestPredictionType(Date from, Date to) {
        return PredictionType.CLIMATE;
    }

    private static WeatherPeriod processResponse(ApiResponse response, Date from, Date to, Location location) {
        return null;
    }

}
