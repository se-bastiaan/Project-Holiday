package nl.teamone.projectholiday.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import nl.teamone.projectholiday.Preferences;
import nl.teamone.projectholiday.R;
import nl.teamone.projectholiday.ui.activities.ExplanationActivity;
import nl.teamone.projectholiday.ui.activities.MainActivity;
import nl.teamone.projectholiday.ui.activities.PlanActivity;
import nl.teamone.projectholiday.utils.PrefUtils;

public class NoPlanFragment extends Fragment {

    public static NoPlanFragment getInstance() {
        NoPlanFragment fragment = new NoPlanFragment();
        fragment.setHasOptionsMenu(true);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_noplan, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
    }

    @OnClick(R.id.plan_button)
    public void planClick(View v) {
        PlanActivity.startActivityForResult(getActivity());
    }

    /**
     * Inflate menu
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_plan, menu);
        menu.findItem(R.id.action_remove).setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }

    /**
     * OnClick of menu items
     * @param item {@link MenuItem}
     * @return {@link Boolean}
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_explanation:
                Intent explanationIntent = new Intent(getActivity(), ExplanationActivity.class);
                startActivity(explanationIntent);
                getActivity().finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
