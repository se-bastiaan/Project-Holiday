package nl.teamone.projectholiday.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;

import com.viewpagerindicator.UnderlinePageIndicator;

import butterknife.InjectView;
import butterknife.OnClick;
import nl.teamone.projectholiday.Preferences;
import nl.teamone.projectholiday.R;
import nl.teamone.projectholiday.ui.fragments.ExplanationFragment;
import nl.teamone.projectholiday.ui.adapters.ExplanationFragmentAdapter;
import nl.teamone.projectholiday.utils.PrefUtils;

/**
 * Activity that explain how the app works
 */
public class ExplanationActivity extends BaseActivity {

    @InjectView(R.id.view_pager)
    ViewPager mViewPager;
    @InjectView(R.id.indicator)
    UnderlinePageIndicator mIndicator;
    @InjectView(R.id.next_button)
    ImageButton mNextButton;
    @InjectView(R.id.prev_button)
    ImageButton mPrevButton;

    /**
     * Activity lifecycle onCreate
     * @param savedInstanceState {@link Bundle}
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_explanation);

        /**
         * Create fragment array and pass it to the FragmentAdapter
         * Then set it on {@link ViewPager} and set the {@link ViewPager} on the {@link UnderlinePageIndicator}
         */
        Fragment[] fragments = new Fragment[]{
                ExplanationFragment.getInstance(R.drawable.explanation1, R.string.explanation1),
                ExplanationFragment.getInstance(R.drawable.explanation2, R.string.explanation2),
                ExplanationFragment.getInstance(R.drawable.explanation2, R.string.explanation3),
                ExplanationFragment.getInstance(R.drawable.explanation4, R.string.explanation4, R.string.proceed)
        };
        PagerAdapter adapter = new ExplanationFragmentAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);
        mIndicator.setViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(mOnPageChangeListener);
        mPrevButton.setVisibility(View.GONE);

        getSupportActionBar().setTitle(getString(R.string.welcome, getString(R.string.app_name)));
    }

    /**
     * OnClick listener for the next button
     * @param v {@link View}
     */
    @OnClick(R.id.next_button)
    public void nextPage(View v) {
        mViewPager.arrowScroll(View.FOCUS_RIGHT);
    }

    /**
     * OnClick listener for the prev button
     * @param v {@link View}
     */
    @OnClick(R.id.prev_button)
    public void previousPage(View v) {
        mViewPager.arrowScroll(View.FOCUS_LEFT);
    }

    /**
     * Listener method to be called by fragment when a certain done button is clicked
     * @param v {@link View}
     */
    public void doneClick(View v) {
        PrefUtils.save(this, Preferences.EXPLANATION_GIVEN, true);

        Intent i = new Intent(this, MainActivity.class);
        i.putExtra(MainActivity.REDIRECT_TO_PLAN, true);
        startActivity(i);
        finish();
    }

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

        @Override
        public void onPageSelected(int position) {
            if(position == 0) {
                mPrevButton.setVisibility(View.GONE);
            } else {
                mPrevButton.setVisibility(View.VISIBLE);
            }

            if(position == mViewPager.getAdapter().getCount() - 1) {
                mNextButton.setVisibility(View.GONE);
            } else {
                mNextButton.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {}
    };

}
