package nl.teamone.projectholiday.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;
import java.util.Timer;

import nl.teamone.projectholiday.Preferences;
import nl.teamone.projectholiday.R;
import nl.teamone.projectholiday.api.objects.Location;
import nl.teamone.projectholiday.ui.fragments.NoPlanFragment;
import nl.teamone.projectholiday.ui.fragments.PlanFragment;
import nl.teamone.projectholiday.utils.PrefUtils;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * The main activity of the application. If the user has a planned trip, then this activity will show the right fragment
 */
public class MainActivity extends BaseActivity {

    public static final String REDIRECT_TO_PLAN = "redir_to_plan";

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
        }

        if(getIntent().hasExtra(REDIRECT_TO_PLAN)) {
            PlanActivity.startActivityForResult(this);
        }

        if(!PrefUtils.get(this, Preferences.HAS_PLAN, false)) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, NoPlanFragment.getInstance()).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, PlanFragment.getInstance()).commit();
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
