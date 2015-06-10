package nl.teamone.projectholiday.background;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by IAmHeavenly on 10-6-2015.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context arg0, Intent arg1) {
        // For our recurring task, we'll just display a message
        //TODO: change this to checking the weather
        Toast.makeText(arg0, "I'm running", Toast.LENGTH_SHORT).show();

    }

}
