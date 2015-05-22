package nl.teamone.projectholiday.WeatherData;

public enum PredictionType {
    NODATA,         // No Data available.
    CLIMATE,        // Prediction based on climate date, very inaccurate and rough.
    LONGTERM,       // Prediction based on long-term predictions, inaccurate.
    FORECAST,       // Prediction based on short-term forecast, as accurate as predictions go.
    CURRENT         // Current weather conditions, as accurate as measurements go.
}
