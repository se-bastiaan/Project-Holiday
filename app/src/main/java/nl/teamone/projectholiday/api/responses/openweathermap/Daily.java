package nl.teamone.projectholiday.api.responses.openweathermap;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Daily {

    public int dt;
    @SerializedName("main")
    public Temperature temp;
    public double pressure;
    public int humidity;
    public Wind wind;
    public Rain rain;
    public Clouds clouds;
    public Rain snow;
    public List<WeatherBlock> weather;

}
