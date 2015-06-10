package nl.teamone.projectholiday.api.services;

import nl.teamone.projectholiday.api.responses.places.Response;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

// https://maps.googleapis.com/maps/api/place/autocomplete/json?input=ban&types=(cities)&key=AIzaSyArhuqi6QvlYqHgppRrR_3KoUeQyje9yWo
public interface PlacesService {

    String API_KEY = "AIzaSyArhuqi6QvlYqHgppRrR_3KoUeQyje9yWo";

    @GET("/place/autocomplete/json")
    Observable<Response> getPredictions(@Query("input") String query,
                                            @Query("types") String types,
                                            @Query("key") String key);

}
