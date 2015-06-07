package nl.teamone.projectholiday;

import android.app.Application;

public class HolidayApplication extends Application {

    private static HolidayApplication sThis;

    @Override
    public void onCreate() {
        super.onCreate();
        sThis = this;
    }

    public static HolidayApplication getInstance() {
        return sThis;
    }

}