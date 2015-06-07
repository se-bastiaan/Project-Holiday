package nl.teamone.projectholiday.WeatherData.ResponseOpenWeatherMap;

import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

public interface IOpenWeatherMap {

    String apiKey = "dce718f5c13cf6686383c372a35bdf7b";

    @GET("/data/2.5/forecast/daily")
    @Headers("x-api-key: " + apiKey)
    Response getWeatherForeCast(@Query("q") String query,
                                @Query("cnt") String dayCount,
                                @Query("mode") String mode);

}
