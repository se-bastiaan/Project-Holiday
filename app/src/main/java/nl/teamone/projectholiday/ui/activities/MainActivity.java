package nl.teamone.projectholiday.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;
import java.util.Timer;

import nl.teamone.projectholiday.Preferences;
import nl.teamone.projectholiday.R;
import nl.teamone.projectholiday.api.objects.Location;
import nl.teamone.projectholiday.utils.PrefUtils;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * The main activity of the application. If the user has a planned trip, then this activity will show the right fragment
 */
public class MainActivity extends BaseActivity {

    /**
     * Android Lifecycle onCreate
     * @param savedInstanceState {@link Bundle}
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_main);

        if(!PrefUtils.get(this, Preferences.EXPLANATION_GIVEN, false)) {
            Intent intent = new Intent(this, ExplanationActivity.class);
            startActivity(intent);
            finish();
            return;
        } else if (!PrefUtils.get(this, Preferences.HAS_PLAN, false)) {
            Intent intent = new Intent(this, PlanActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        getData();
    }

    private void getData() {
        Location.find("Vossendijk, Nijmegen").observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<Location>>() {
            @Override
            public void call(List<Location> locations) {
                for (Location loc : locations)
                    Timber.d("Location: %s", loc.toString());
            }
        });
    }

}
