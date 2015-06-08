package nl.teamone.projectholiday.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import nl.teamone.projectholiday.Preferences;
import nl.teamone.projectholiday.R;
import nl.teamone.projectholiday.utils.PrefUtils;

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

        if(!PrefUtils.contains(this, Preferences.EXPLANATION_GIVEN)) {
            Intent intent = new Intent(this, ExplanationActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
