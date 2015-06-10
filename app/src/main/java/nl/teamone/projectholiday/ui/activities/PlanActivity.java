package nl.teamone.projectholiday.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.InjectView;
import butterknife.OnClick;
import nl.teamone.projectholiday.R;
import nl.teamone.projectholiday.api.objects.Location;
import nl.teamone.projectholiday.api.objects.PlanData;
import nl.teamone.projectholiday.ui.fragments.DatePickerFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class PlanActivity extends BaseActivity {

    public static final int DATA = 1408;
    public static final String EXTRA_DATA = "extra_data";

    private Calendar mSelectedDate;
    private Boolean mDresses;

    @InjectView(R.id.location_input)
    EditText mLocationEditText;
    @InjectView(R.id.date_input)
    EditText mDateEditText;
    @InjectView(R.id.progress_seekbar)
    DiscreteSeekBar mSeekBar;
    @InjectView(R.id.progress_input)
    EditText mNightsEditText;
    @InjectView(R.id.dresses_yes)
    ToggleButton mDressesYesToggle;
    @InjectView(R.id.dresses_no)
    ToggleButton mDressesNoToggle;

    public static void startActivityForResult(Activity activity) {
        Intent intent = new Intent(activity, PlanActivity.class);
        activity.startActivityForResult(intent, DATA);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_plan);

        mDateEditText.setOnTouchListener(mOnDateTouchListener);
        mSeekBar.setOnProgressChangeListener(mOnProgressChangeListener);

        /** Default data */
        mSeekBar.setProgress(4);
        mNightsEditText.setText("4");
        mDateCallback.onDateSet(Calendar.getInstance());
        onDressesClick(false);

        /** Prevent EditText selection */
        mSeekBar.requestFocus();

        getSupportActionBar().setTitle(R.string.plan_new_trip);
    }

    @OnClick(R.id.plan_button)
    public void onPlanClick(View v) {
        mLocationEditText.setError(null);

        Location.find(mLocationEditText.getText().toString())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Location>>() {
                    @Override
                    public void call(List<Location> locations) {
                        if (locations.size() > 0) {
                            Intent intent = new Intent();
                            intent.putExtra(EXTRA_DATA, new PlanData(locations.get(0), mSelectedDate.getTime(), Integer.parseInt(mNightsEditText.getText().toString()), mDresses));
                            setResult(RESULT_OK, intent);
                            finish();
                        } else {
                            mLocationEditText.setError(getString(R.string.location_notfound));
                        }
                    }
                });
    }

    @OnClick(R.id.dresses_no)
    public void onDressesNoClick(View v) {
        onDressesClick(false);
    }

    @OnClick(R.id.dresses_yes)
    public void onDressesYesClick(View v) {
        onDressesClick(true);
    }

    private void onDressesClick(Boolean yes) {
        mDresses = yes;

        mDressesYesToggle.setChecked(mDresses);
        mDressesNoToggle.setChecked(!mDresses);
    }

    private DatePickerFragment.Callback mDateCallback = new DatePickerFragment.Callback() {
        @Override
        public void onDateSet(Calendar c) {
            mSelectedDate = c;
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ROOT);
            String formattedDate = sdf.format(mSelectedDate.getTime());
            mDateEditText.setText(formattedDate);
        }
    };

    private View.OnTouchListener mOnDateTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (getSupportFragmentManager().findFragmentByTag("date_picker") == null) {
                DatePickerFragment pickerFragment = DatePickerFragment.getInstance(mDateCallback);
                pickerFragment.show(getSupportFragmentManager(), "date_picker");
            }
            return true;
        }
    };

    private DiscreteSeekBar.OnProgressChangeListener mOnProgressChangeListener = new DiscreteSeekBar.OnProgressChangeListener() {
        @Override
        public void onProgressChanged(DiscreteSeekBar discreteSeekBar, int i, boolean b) {
            mNightsEditText.setText(String.format("%d", i));
        }

        @Override
        public void onStartTrackingTouch(DiscreteSeekBar discreteSeekBar) { }

        @Override
        public void onStopTrackingTouch(DiscreteSeekBar discreteSeekBar) { }
    };
}
