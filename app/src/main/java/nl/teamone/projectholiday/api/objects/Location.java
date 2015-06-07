package nl.teamone.projectholiday.api.objects;

import android.content.Context;
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

    private Location(Address address) {
        this.fullDisplayName = String.format("%s, %s", address.getLocality(), address.getCountryName());
        this.countryFull = address.getCountryName();
        this.countryISO = address.getCountryCode();
        this.city = address.getLocality();
        this.latitude = address.getLatitude();
        this.longitude = address.getLongitude();
    }

    public static Observable<List<Location>> find(String location) {
        return Observable.just(location).subscribeOn(Schedulers.newThread())
                .map(new Func1<String, List<Location>>() {
                    @Override
                    public List<Location> call(String s) {
                        Geocoder geocoder = new Geocoder(HolidayApplication.getInstance());
                        try {
                            List<Location> returnList = new ArrayList<>();
                            for(Address address :  geocoder.getFromLocationName(s, 20)) {
                                returnList.add(new Location(address));
                            }
                            return returnList;
                        } catch (IOException e) {
                            return null;
                        }
                    }
                });
    }

}
