package nl.teamone.projectholiday.api.services;

import nl.teamone.projectholiday.api.responses.openweathermap.Response;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;
import rx.Observable;

public interface OpenWeatherMapService {

    String API_KEY = "dce718f5c13cf6686383c372a35bdf7b";

    @GET("/data/2.5/forecast/daily")
    @Headers("x-api-key: " + API_KEY)
    Observable<Response> getWeatherForeCast(@Query("q") String query,
                                            @Query("cnt") String dayCount,
                                            @Query("mode") String mode);

}
