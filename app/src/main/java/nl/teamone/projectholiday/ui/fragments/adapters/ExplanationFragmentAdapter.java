package nl.teamone.projectholiday.ui.fragments.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Simple FragmentAdapter for a ViewPager
 */
public class ExplanationFragmentAdapter extends FragmentStatePagerAdapter {

    private Fragment[] content;

    public ExplanationFragmentAdapter(FragmentManager fm, Fragment[] objects) {
        super(fm);
        this.content = objects;
    }

    @Override
    public Fragment getItem(int position) {
        return content[position];
    }

    @Override
    public int getCount() {
        return content.length;
    }

}