package nl.teamone.projectholiday.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import nl.teamone.projectholiday.R;
import nl.teamone.projectholiday.ui.activities.PlanActivity;

public class NoPlanFragment extends Fragment {

    public static NoPlanFragment getInstance() {
        return new NoPlanFragment();
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

}
