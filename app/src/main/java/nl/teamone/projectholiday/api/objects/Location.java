package nl.teamone.projectholiday.api.objects;

import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nl.teamone.projectholiday.HolidayApplication;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class Location {

    public final String fullDisplayName;

    public final String countryFull;
    public final String countryISO;

    public final String city;

    public final double latitude;
    public final double longitude;

    /**
     * Constructor for Location.
     * Sets values to values of address
     * @param address
     */
    private Location(Address address) {
        fullDisplayName = String.format("%s, %s", address.getLocality(), address.getCountryName());
        countryFull = address.getCountryName();
        countryISO = address.getCountryCode();
        city = address.getLocality();
        latitude = address.getLatitude();
        longitude = address.getLongitude();
    }

    /**
     * Converts a string to a location
     * @param location
     * @return location
     */
    public static Observable<List<Location>> find(String location) {
        return Observable.just(location).subscribeOn(Schedulers.newThread())
                .map(new Func1<String, List<Location>>() {
                    @Override
                    public List<Location> call(String s) {
                        Geocoder geocoder = new Geocoder(HolidayApplication.getInstance());
                        try {
                            List<Location> returnList = new ArrayList<>();
                            for (Address address : geocoder.getFromLocationName(s, 20)) {
                                returnList.add(new Location(address));
                            }
                            return returnList;
                        } catch (IOException e) {
                            return null;
                        }
                    }
                });
    }

    @Override
    public String toString() {
        return fullDisplayName;
    }
}
