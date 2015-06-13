package nl.teamone.projectholiday.ui.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

import nl.teamone.projectholiday.R;

/**
 * Standard DatePickerFragment to select a date
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private Callback mCallback;

    public static DatePickerFragment getInstance(Callback callback) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setCallback(callback);
        return fragment;
    }

    private void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        DatePickerDialog dialog;
        if(Build.VERSION_CODES.LOLLIPOP <= Build.VERSION.SDK_INT) {
            dialog = new DatePickerDialog(getActivity(), R.style.Theme_ProjectHoliday_Dialog, this, year, month, day);
        } else {
            dialog = new DatePickerDialog(getActivity(), this, year, month, day);
        }

        Calendar today = Calendar.getInstance(Locale.ROOT);
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        dialog.getDatePicker().setMinDate(today.getTimeInMillis());
        return dialog;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);

        /**
         * Make selection before today not possible
         */
        Calendar today = Calendar.getInstance(Locale.ROOT);
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        if(c.before(today)) {
            Toast.makeText(getActivity(), R.string.date_notpossible, Toast.LENGTH_SHORT).show();
            return;
        }

        if (mCallback != null)
            mCallback.onDateSet(c);
    }

    public interface Callback {
        void onDateSet(Calendar c);
    }

}