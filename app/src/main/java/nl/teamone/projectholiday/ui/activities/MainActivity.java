package nl.teamone.projectholiday.ui.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import nl.teamone.projectholiday.Preferences;
import nl.teamone.projectholiday.R;
import nl.teamone.projectholiday.api.objects.PlanData;
import nl.teamone.projectholiday.ui.fragments.NoPlanFragment;
import nl.teamone.projectholiday.ui.fragments.PlanFragment;
import nl.teamone.projectholiday.utils.NetworkUtils;
import nl.teamone.projectholiday.utils.PrefUtils;

/**
 * The main activity of the application. If the user has a planned trip, then this activity will show the right fragment
 */
public class MainActivity extends BaseActivity {

    public static final String REDIRECT_TO_PLAN = "redir_to_plan";

    private PlanData mPlanData;

    /**
     * Android Lifecycle onCreate
     * @param savedInstanceState {@link Bundle}
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_main);

        if(!NetworkUtils.isNetworkConnected()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(getResources().getString(R.string.no_connection))
                    .setCancelable(false)
                    .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                            MainActivity.this.finish();
                        }
                    }).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
            return;
        }

        if(!PrefUtils.get(this, Preferences.EXPLANATION_GIVEN, false)) {
            Intent intent = new Intent(this, ExplanationActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        if(getIntent().hasExtra(REDIRECT_TO_PLAN)) {
            PlanActivity.startActivityForResult(this);
        }

        if((mPlanData = PlanData.getFromPrefs(this)) == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, NoPlanFragment.getInstance()).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, PlanFragment.getInstance(mPlanData)).commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            if(requestCode == PlanActivity.DATA) {
                mPlanData = data.getParcelableExtra(PlanActivity.EXTRA_DATA);
                mPlanData.saveToPrefs(this);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, PlanFragment.getInstance(mPlanData)).commit();
            }
        }
    }
}
