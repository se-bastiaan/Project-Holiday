package nl.teamone.projectholiday.api.services;

import nl.teamone.projectholiday.api.responses.weatherunderground.ApiResponse;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface WeatherUnderGroundService {

    String API_KEY = "8d847184ee549f01";

    @GET("/api/" + API_KEY + "/planner_{start}{end}/q/{country}/{city}.json")
    Observable<ApiResponse> getClimateData(@Path("start") String start,
                                        @Path("end") String end,
                                        @Path("country") String country,
                                        @Path("city") String city);

    @GET("/api/" + API_KEY + "/planner_{start}{end}/q/{string}.json")
    ApiResponse getClimateData(@Path("start") String start,
                                           @Path("end") String end,
                                           @Path("string") String string);


}
