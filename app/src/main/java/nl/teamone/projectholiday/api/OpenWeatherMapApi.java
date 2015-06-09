package nl.teamone.projectholiday.api;

import java.util.Date;

import nl.teamone.projectholiday.api.objects.Location;
import nl.teamone.projectholiday.api.objects.PredictionType;
import nl.teamone.projectholiday.api.objects.WeatherDay;
import nl.teamone.projectholiday.api.objects.WeatherPeriod;
import nl.teamone.projectholiday.api.responses.openweathermap.Daily;
import nl.teamone.projectholiday.api.responses.openweathermap.Response;
import nl.teamone.projectholiday.api.services.OpenWeatherMapService;
import retrofit.RestAdapter;
import rx.Observable;
import rx.functions.Func1;

public class OpenWeatherMapApi extends DataRetriever {

    public static final String requiredMode = "json";
    public static final String baseURL = "http://api.openweathermap.org";

    public static Observable<Response> getCurrentWeather(final Location loc) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(baseURL)
                .build();

        OpenWeatherMapService apiRetriever = restAdapter.create(OpenWeatherMapService.class);

        return apiRetriever.getCurrentWeather(
                Double.toString(loc.latitude),
                Double.toString(loc.longitude),
                requiredMode);
    }

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

        OpenWeatherMapService apiRetriever = restAdapter.create(OpenWeatherMapService.class);

        return apiRetriever.getWeatherForecastByCoordinates(
                Double.toString(loc.latitude),
                Double.toString(loc.longitude),
                Integer.toString(getDuration(from, to)),
                requiredMode)
                .map(new Func1<Response, WeatherPeriod>() {
                    @Override
                    public WeatherPeriod call(Response response) {
                        return processResponse(response, from, to, loc);
                    }
                });
    }

    /**
     * If date is within 16 days, prediction type is accurate,
     * else prediction type is shit.
     * @param from
     * @param to
     * @return FORECAST / NODATA
     */
    public static PredictionType getBestPredictionType(Date from, Date to) {
        Date now = new Date();
        if ((to.getTime() - (15* DAY_IN_MILLIS)) > now.getTime())
            return PredictionType.NODATA;
        return PredictionType.FORECAST;
    }

    /**
     * Converts location to string
     * @param location
     * @return location.city = location.countryISO
     */
    private static String getQueryLocation(Location location) {
        return location.city.toLowerCase() + "," + location.countryISO.toLowerCase();
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
    private static WeatherPeriod processResponse(Response response, Date from, Date to, Location location) {
        WeatherPeriod period = new WeatherPeriod(from, to);
        if (response.cod != 200) {
            // Did not return with HTML status code 200 (OK)
            // Return the period immediately. period.bestPredictionType will be equal to NODATA
            return period;
        }

        // We got a valid response, let's populate the data:
        for (int i = 0; i < response.cnt && i < response.list.size(); i++) {
            Date dailyDate = new Date(from.getTime() + (i * DAY_IN_MILLIS));

            WeatherDay day = processDay(dailyDate, location, response.list.get(i));

            period.addWeatherDay(day);
        }

        period.calculateTotalDays();
        return period;
    }

    private static WeatherDay processDay(Date date, Location loc, Daily dayInfo) {
        WeatherDay day = new WeatherDay(date, loc, PredictionType.FORECAST);

        // Set the data to match the day.
        day.setRainAmountInMillimeter((int) dayInfo.rain._3h);
        if (dayInfo.weather.description.equalsIgnoreCase("Rain"))
            day.setRainPercentChance(100);
        else if (dayInfo.weather.description.equalsIgnoreCase("Drizzle"))
            day.setRainPercentChance(60);
        else if (dayInfo.weather.description.equalsIgnoreCase("Thunderstorm"))
            day.setRainPercentChance(100);
        else
            day.setRainPercentChance(0);
        day.setTemperatureHigh((int) dayInfo.temp.max);
        day.setTemperatureLow((int) dayInfo.temp.min);
        day.setTemperatureMean((int) dayInfo.temp.day);
        day.setWindSpeed((int) dayInfo.wind.speed);

        return day;
    }

}
