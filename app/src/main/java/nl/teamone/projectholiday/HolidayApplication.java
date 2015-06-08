package nl.teamone.projectholiday;

import android.app.Application;

import timber.log.Timber;

public class HolidayApplication extends Application {

    private static HolidayApplication sThis;

    public static HolidayApplication getInstance() {
        return sThis;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sThis = this;

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

}
