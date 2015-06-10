package nl.teamone.projectholiday.api.objects;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Calendar;
import java.util.Date;

import nl.teamone.projectholiday.Preferences;
import nl.teamone.projectholiday.utils.PrefUtils;

public class PlanData implements Parcelable {
    public final Location location;
    public final Date departureDate;
    public final int nights;
    public final boolean enableDresses;

    public PlanData(Location location, Date departureDate, int nights, boolean enableDresses) {
        this.location = location;
        this.departureDate = departureDate;
        this.nights = nights;
        this.enableDresses = enableDresses;
    }

    protected PlanData(Parcel in) {
        location = (Location) in.readValue(Location.class.getClassLoader());
        long tmpDepartureDate = in.readLong();
        departureDate = tmpDepartureDate != -1 ? new Date(tmpDepartureDate) : null;
        nights = in.readInt();
        enableDresses = in.readByte() != 0x00;
    }

    public Date getReturnDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(departureDate);
        calendar.add(Calendar.DAY_OF_YEAR, nights);
        return calendar.getTime();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(location);
        dest.writeLong(departureDate != null ? departureDate.getTime() : -1L);
        dest.writeInt(nights);
        dest.writeByte((byte) (enableDresses ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<PlanData> CREATOR = new Parcelable.Creator<PlanData>() {
        @Override
        public PlanData createFromParcel(Parcel in) {
            return new PlanData(in);
        }

        @Override
        public PlanData[] newArray(int size) {
            return new PlanData[size];
        }
    };

    public void saveToPrefs(Context context) {
        PrefUtils.save(context, Preferences.PLAN, this);
    }

    public static PlanData getFromPrefs(Context context) {
        return PrefUtils.get(context, Preferences.PLAN, PlanData.class);
    }
}
