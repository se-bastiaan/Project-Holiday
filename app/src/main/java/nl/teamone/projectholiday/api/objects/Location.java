package nl.teamone.projectholiday.api.objects;

import android.location.Address;
import android.location.Geocoder;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nl.teamone.projectholiday.HolidayApplication;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class Location implements Parcelable {

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
        fullDisplayName = String.format("%s, %s", (address.getLocality() == null ? address.getSubLocality() : address.getLocality()), address.getCountryName());
        countryFull = address.getCountryName();
        countryISO = address.getCountryCode();
        city = (address.getLocality() == null ? address.getSubLocality() : address.getLocality());
        latitude = address.getLatitude();
        longitude = address.getLongitude();
    }

    /**
     * Find location lat and long by given string name
     * @param location {@link String}
     * @return {@link Observable}
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

    protected Location(Parcel in) {
        fullDisplayName = in.readString();
        countryFull = in.readString();
        countryISO = in.readString();
        city = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fullDisplayName);
        dest.writeString(countryFull);
        dest.writeString(countryISO);
        dest.writeString(city);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
}
