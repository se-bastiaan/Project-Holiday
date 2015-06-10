package nl.teamone.projectholiday.api.objects;

import nl.teamone.projectholiday.R;
import nl.teamone.projectholiday.api.responses.openweathermap.Daily;

public class WeatherData {

    public final String weatherType;
    public final Double temperature;
    public final int imageRes;

    public WeatherData(Daily response) {
        this.weatherType = response.weather.get(0).main;
        this.temperature = response.temp.day;

        switch(response.weather.get(0).icon) {
            // Clear sky
            case "01d":
            case "01n":
                imageRes = R.drawable.sunny;
                break;
            // Few clouds
            case "02d":
            case "02n":
                imageRes = R.drawable.clouds;
                break;
            // Scattered clouds
            case "03d":
            case "03n":
            // Broken clouds
            case "04d":
            case "04n":
                imageRes = R.drawable.clouds_a_lot;
                break;
            // Shower rain
            case "09d":
            case "09n":
                imageRes = R.drawable.pouring;
                break;
            // Rain
            case "10d":
            case "10n":
                imageRes = R.drawable.rain;
                break;
            // Thunderstorm
            case "11d":
            case "11n":
                imageRes = R.drawable.thunder;
                break;
            // Snow
            case "13d":
            case "13n":
                imageRes = R.drawable.snow;
                break;
            // Mist
            case "50d":
            case "50n":
                imageRes = R.drawable.mist;
                break;
            default:
                imageRes = R.drawable.celsius;
        }
    }

}
