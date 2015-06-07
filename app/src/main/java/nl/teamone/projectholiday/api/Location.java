package nl.teamone.projectholiday.api;

public class Location {

    public final String fullDisplayName;

    public final String countryFull;
    public final String countryISO;

    public final String city;

    public final double latitude;
    public final double longitude;

    public Location(String loc) {
        //TODO: Find real location from location string.
        this.fullDisplayName = "";
        this.countryFull = "";
        this.countryISO = "";
        this.city = "";
        this.latitude = 0.0;
        this.longitude = 0.0;
    }

}
