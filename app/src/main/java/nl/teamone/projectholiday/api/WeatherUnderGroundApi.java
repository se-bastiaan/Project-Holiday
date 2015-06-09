package nl.teamone.projectholiday.api;

import java.util.Calendar;
import java.util.Date;

import nl.teamone.projectholiday.api.objects.Location;
import nl.teamone.projectholiday.api.objects.PredictionType;
import nl.teamone.projectholiday.api.objects.WeatherDay;
import nl.teamone.projectholiday.api.objects.WeatherPeriod;
import nl.teamone.projectholiday.api.responses.weatherunderground.ApiResponse;
import nl.teamone.projectholiday.api.services.WeatherUnderGroundService;
import retrofit.RestAdapter;
import rx.Observable;
import rx.functions.Func1;

public class WeatherUnderGroundApi extends DataRetriever {

    public static final String baseURL = "http://api.wunderground.com";

    /**
     * Gets WeatherData from location from date from to date to
     * @param loc
     * @param from
     * @param to
     * @return WeatherData
     */
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

    /**
     * Converts date to string
     * @param date
     * @return month + day
     */
    public static String createDateString(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String formattedMonth = String.format("%02d", cal.get(Calendar.MONTH));
        String formattedDay = String.format("%02d", cal.get(Calendar.DAY_OF_MONTH));
        return formattedMonth + formattedDay;
    }

    /**
     *
     * @param from
     * @param to
     * @return PredictionType.CLIMATE
     */
    public static PredictionType getBestPredictionType(Date from, Date to) {
        return PredictionType.CLIMATE;
    }

    /**
     * If response is not OK, returns empty weatherPeriod. Otherwise,
     * adds a WeatherDay for every day of the vacation toe a WeatherPeriod and
     * returns that new WeatherPeriod
     * @param response
     * @param from
     * @param to
     * @param location
     * @return period
     */
    private static WeatherPeriod processResponse(ApiResponse response, Date from, Date to, Location location) {
        WeatherPeriod period = new WeatherPeriod(from, to);
        if (response.response.error != null) {
            // Got an error.
            // Return the period immediately. period.bestPredictionType will be equal to NODATA
            return period;
        }

        // Got a valid response, let's populate the data:
        int totalDays = getDuration(from, to);
        for (int i = 0; i < totalDays; i++) {
            Date dailyDate = new Date(from.getTime() + (i * DAY_IN_MILLIS));
            WeatherDay day = new WeatherDay(dailyDate, location, PredictionType.CLIMATE);

            // Set the data to match the day.
            day.setRainAmountInMillimeter((int)response.trip.precip.avg.cm);
            day.setRainPercentChance(response.trip.chance_of.chanceofrainday.percentage);
            day.setTemperatureHigh(response.trip.temp_high.avg.C);
            day.setTemperatureLow(response.trip.temp_low.avg.C);
            day.setTemperatureMean((response.trip.temp_high.avg.C + response.trip.temp_low.avg.C) / 2);
            day.setWindSpeed(response.trip.chance_of.chanceofwindyday.percentage / 3);

            period.addWeatherDay(day);
        }

        period.calculateTotalDays();
        return period;
    }

}
