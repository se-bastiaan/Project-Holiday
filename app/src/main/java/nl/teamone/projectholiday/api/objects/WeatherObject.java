package nl.teamone.projectholiday.api.objects;

public class WeatherObject {

    public final WeatherPeriod period;
    public final WeatherData data;

    public WeatherObject(WeatherPeriod period, WeatherData data) {
        this.period = period;
        this.data = data;
    }

}
