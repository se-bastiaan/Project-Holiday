package nl.teamone.projectholiday.api.responses.openweathermap;

import com.google.gson.annotations.SerializedName;

public class Temperature {

    @SerializedName("temp")
    public double day;
    @SerializedName("temp_min")
    public double min;
    @SerializedName("temp_max")
    public double max;

}
