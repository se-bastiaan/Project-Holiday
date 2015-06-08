package nl.teamone.projectholiday.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.Optional;
import nl.teamone.projectholiday.R;
import nl.teamone.projectholiday.ui.activities.ExplanationActivity;

public class ExplanationFragment extends Fragment {

    private final static String IMAGE = "img", TEXT = "text", DONE = "done";

    private int mImageRes = -1, mTextRes = -1, mDoneRes = -1;
    private ExplanationActivity mActivity;

    @Optional
    @InjectView(R.id.done)
    Button mDoneButton;
    @InjectView(R.id.slide_image)
    ImageView mImage;
    @InjectView(R.id.slide_text)
    TextView mText;

    public static ExplanationFragment getInstance(int imageRes, int textRes) {
        return getInstance(imageRes, textRes, -1);
    }

    public static ExplanationFragment getInstance(int imageRes, int textRes, int doneRes) {
        ExplanationFragment fragment = new ExplanationFragment();
        Bundle args = new Bundle();
        args.putInt(IMAGE, imageRes);
        args.putInt(TEXT, textRes);
        if(doneRes != -1)
            args.putInt(DONE, doneRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageRes = getArguments().getInt(IMAGE);
        mTextRes = getArguments().getInt(TEXT);
        if(getArguments().containsKey(DONE))
            mDoneRes = getArguments().getInt(DONE);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (ExplanationActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_explanation, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);

        mImage.setImageResource(mImageRes);
        mText.setText(mTextRes);
        if(mDoneRes != -1) {
            mDoneButton.setVisibility(View.VISIBLE);
            mDoneButton.setText(mDoneRes);
        }
    }

    @OnClick(R.id.next_button)
    public void nextPage(View v) {
        if(mActivity != null)
            mActivity.nextPage(v);
    }

    @OnClick(R.id.prev_button)
    public void previousPage(View v) {
        if(mActivity != null)
            mActivity.previousPage(v);
    }

    @Optional
    @OnClick(R.id.done)
    public void doneClick(View v) {
        if(mActivity != null)
            mActivity.doneClick(v);
    }

}
