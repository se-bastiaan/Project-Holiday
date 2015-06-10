package nl.teamone.projectholiday.api;

import java.util.List;

import nl.teamone.projectholiday.api.responses.places.Prediction;
import nl.teamone.projectholiday.api.responses.places.Response;
import nl.teamone.projectholiday.api.services.PlacesService;
import retrofit.RestAdapter;
import rx.Observable;
import rx.functions.Func1;

public class PlacesApi {

    public static final String BASE_URL = "https://maps.googleapis.com/maps/api/";

    public static Observable<List<Prediction>> getPredictions(String input) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .build();

        PlacesService apiRetriever = restAdapter.create(PlacesService.class);

        return apiRetriever.getPredictions(input, "(cities)", PlacesService.API_KEY).map(new Func1<Response, List<Prediction>>() {
            @Override
            public List<Prediction> call(Response response) {
                return response.predictions;
            }
        });
    }

}
