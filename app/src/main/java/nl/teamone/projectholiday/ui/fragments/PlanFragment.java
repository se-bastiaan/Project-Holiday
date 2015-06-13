package nl.teamone.projectholiday.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nl.teamone.projectholiday.Preferences;
import nl.teamone.projectholiday.R;
import nl.teamone.projectholiday.algorithm.Clothing;
import nl.teamone.projectholiday.algorithm.PackingList;
import nl.teamone.projectholiday.api.API;
import nl.teamone.projectholiday.api.objects.PlanData;
import nl.teamone.projectholiday.api.objects.WeatherData;
import nl.teamone.projectholiday.api.objects.WeatherObject;
import nl.teamone.projectholiday.api.objects.WeatherPeriod;
import nl.teamone.projectholiday.background.AlarmReceiver;
import nl.teamone.projectholiday.ui.activities.ExplanationActivity;
import nl.teamone.projectholiday.ui.activities.MainActivity;
import nl.teamone.projectholiday.ui.adapters.PackingListAdapter;
import nl.teamone.projectholiday.utils.PrefUtils;
import rx.Observable;
import rx.android.app.AppObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func2;

public class PlanFragment extends Fragment {

    private static final String PLAN_DATA = "plan_data";

    private PlanData mPlanData;

    @InjectView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @InjectView(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;

    /**
     * Get new instance of PlanFragment
     * @param planData {@link PlanData}
     * @return {@link PlanFragment}
     */
    public static PlanFragment getInstance(PlanData planData) {
        PlanFragment fragment = new PlanFragment();
        Bundle args = new Bundle();
        args.putParcelable(PLAN_DATA, planData);
        fragment.setArguments(args);
        fragment.setHasOptionsMenu(true);
        return fragment;
    }

    /**
     * Lifecycle call
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPlanData = getArguments().getParcelable(PLAN_DATA);
    }

    /**
     * Lifecycle call
     */
    @Override
    public void onResume() {
        super.onResume();
        AlarmReceiver.cancel(getActivity());
    }

    /**
     * Lifecycle call
     */
    @Override
    public void onPause() {
        super.onPause();
        AlarmReceiver.planNew(getActivity());
    }

    /**
     * Lifecycle call
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_plan, container, false);
    }

    /**
     * Lifecycle call
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.trip_to, mPlanData.location.city));

        mSwipeRefreshLayout.setColorSchemeResources(R.color.accent, R.color.primary, R.color.primary_dark);
        mSwipeRefreshLayout.setOnRefreshListener(mOnRefreshListener);

        mOnRefreshListener.onRefresh();
        Observable.just(null).delay(1000, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });

        GridLayoutManager layoutManager = new GridLayoutManager(mRecyclerView.getContext(), 2);
        layoutManager.setSpanSizeLookup(mSpanSizeLookup);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    /**
     * Inflate menu
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_plan, menu);
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
            case R.id.action_remove:
                PrefUtils.remove(getActivity(), Preferences.PLAN);
                Intent mainIntent = new Intent(getActivity(), MainActivity.class);
                startActivity(mainIntent);
                getActivity().finish();
                break;
            case R.id.action_explanation:
                Intent explanationIntent = new Intent(getActivity(), ExplanationActivity.class);
                startActivity(explanationIntent);
                getActivity().finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Listener for the pull-to-refresh
     */
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            AppObservable.bindFragment(PlanFragment.this,
                Observable.zip(
                    API.getWeatherData(mPlanData.location, mPlanData.departureDate, mPlanData.getReturnDate()),
                    API.getCurrentWeather(mPlanData.location),
                    new Func2<WeatherPeriod, WeatherData, WeatherObject>() {
                        @Override
                        public WeatherObject call(WeatherPeriod period, WeatherData weatherData) {
                            return new WeatherObject(period, weatherData);
                        }
                    }
                )
            )
            .doOnError(new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    Toast.makeText(getActivity(), R.string.unknown_error, Toast.LENGTH_SHORT).show();
                }
            })
            .subscribe(new Action1<WeatherObject>() {
                @Override
                public void call(WeatherObject weatherObject) {
                    PackingList packingList = new PackingList(weatherObject.period, mPlanData.enableDresses);
                    List<Clothing> clothingList = packingList.getClothingList();

                    PackingListAdapter adapter = new PackingListAdapter(weatherObject.data, clothingList);
                    mRecyclerView.setAdapter(adapter);
                    mSwipeRefreshLayout.setRefreshing(false);
                    packingList.saveToPrefs(getActivity());
                }
            });
        }
    };

    /**
     * Span lookup for the grid
     */
    private GridLayoutManager.SpanSizeLookup mSpanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
        @Override
        public int getSpanSize(int position) {
            if(position == 0) {
                return 2;
            }
            return 1;
        }
    };

}
