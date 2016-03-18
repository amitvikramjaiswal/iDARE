package com.opensource.app.idare.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.opensource.app.idare.R;
import com.opensource.app.idare.view.activities.MainActivity;

/**
 * Created by ajaiswal on 3/18/2016.
 */
public class MyAccountActiveFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "MyAccountActiveFragment";
    private MainActivity mMainActivity;
    private Context mContext;
    private Button btnAlert;
    private Button btnCallNearestPoliceStation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        mMainActivity = (MainActivity) getActivity();
        mContext = mMainActivity.getApplicationContext();
        setHasOptionsMenu(true);
        mMainActivity.getSupportActionBar().setTitle(mMainActivity.getStringArray(R.array.arr_nav_titles)[0]);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setValues();
        addListeners();
    }

    private void setValues() {

    }

    private void addListeners() {
        btnAlert.setOnClickListener(this);
        btnCallNearestPoliceStation.setOnClickListener(this);
    }

    @Override
    int getFragmentLayoutResourceId() {
        return R.layout.fragment_my_account_active;
    }

    @Override
    void findViews() {
        btnCallNearestPoliceStation = (Button) mRootView.findViewById(R.id.btn_call_nearest_police_station);
        btnAlert = (Button) mRootView.findViewById(R.id.btn_alert);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_call_nearest_police_station:
                mMainActivity.popFragment();
                break;
            case R.id.btn_alert:
                mMainActivity.popFragment();
                break;
            default:
                break;
        }
    }
}
