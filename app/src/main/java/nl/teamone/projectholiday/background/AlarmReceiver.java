package nl.teamone.projectholiday.background;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.util.List;

import nl.teamone.projectholiday.R;
import nl.teamone.projectholiday.algorithm.Clothing;
import nl.teamone.projectholiday.algorithm.PackingList;
import nl.teamone.projectholiday.api.API;
import nl.teamone.projectholiday.api.objects.PlanData;
import nl.teamone.projectholiday.api.objects.WeatherPeriod;
import nl.teamone.projectholiday.ui.activities.MainActivity;
import nl.teamone.projectholiday.utils.PrefUtils;
import rx.functions.Action1;

public class AlarmReceiver extends BroadcastReceiver {

    /**
     * Receive planned alarm
     * @param context {@link Context}
     * @param intent {@link Intent}
     */
    @Override
    public void onReceive(final Context context, Intent intent) {
        final PlanData planData = PlanData.getFromPrefs(context);
        API.getWeatherData(planData.location, planData.departureDate, planData.getReturnDate())
                .subscribe(new Action1<WeatherPeriod>() {
                    @Override
                    public void call(WeatherPeriod period) {
                        PackingList packingList = new PackingList(period, planData.enableDresses);
                        if (packingList.equals(PackingList.getFromPrefs(context))) {
                            Intent resultIntent = new Intent(context, MainActivity.class);
                            PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                                    .setContentIntent(resultPendingIntent)
                                    .setSmallIcon(R.drawable.ic_stat_no)
                                    .setContentTitle(context.getString(R.string.packinglist_changed))
                                    .setContentText(context.getString(R.string.check_changes));

                            AlarmReceiver.planNew(context);

                            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                            notificationManager.notify(1605, notificationBuilder.build());
                            packingList.saveToPrefs(context);
                        }
                    }
                });
    }

    /**
     * Plan new Alarm for packing list check. First cancel current alarms.
     * @param context {@link Context}
     */
    public static void planNew(Context context) {
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
        manager.cancel(pendingIntent);
        int interval = 43200000;
        manager.set(AlarmManager.RTC, System.currentTimeMillis() + interval, pendingIntent);
    }

    /**
     * Cancel current alarm
     * @param context {@link Context}
     */
    public static void cancel(Context context) {
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
        manager.cancel(pendingIntent);
    }


}
