package nl.teamone.projectholiday.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.viewpagerindicator.UnderlinePageIndicator;

import butterknife.InjectView;
import nl.teamone.projectholiday.Preferences;
import nl.teamone.projectholiday.R;
import nl.teamone.projectholiday.ui.fragments.ExplanationFragment;
import nl.teamone.projectholiday.ui.fragments.adapters.ExplanationFragmentAdapter;
import nl.teamone.projectholiday.utils.PrefUtils;

public class ExplanationActivity extends BaseActivity {

    @InjectView(R.id.view_pager)
    ViewPager mViewPager;
    @InjectView(R.id.indicator)
    UnderlinePageIndicator mIndicator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_explanation);

        Fragment[] fragments = new Fragment[]{
                ExplanationFragment.getInstance(R.drawable.tempimage, R.string.app_name),
                ExplanationFragment.getInstance(R.drawable.tempimage, R.string.app_name),
                ExplanationFragment.getInstance(R.drawable.tempimage, R.string.app_name),
                ExplanationFragment.getInstance(R.drawable.tempimage, R.string.app_name, R.string.proceed)
        };
        PagerAdapter adapter = new ExplanationFragmentAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);
        mIndicator.setViewPager(mViewPager);
    }

    public void nextPage(View v) {
        mViewPager.arrowScroll(View.FOCUS_RIGHT);
    }

    public void previousPage(View v) {
        mViewPager.arrowScroll(View.FOCUS_LEFT);
    }

    public void doneClick(View v) {
        PrefUtils.save(this, Preferences.EXPLANATION_GIVEN, true);

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

}
