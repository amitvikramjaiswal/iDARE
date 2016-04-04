package com.opensource.app.idare.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.opensource.app.idare.R;
import com.opensource.app.idare.view.activities.MainActivity;
import com.opensource.app.idare.view.activities.NearBySafeHouseActivity;

/**
 * Created by ajaiswal on 3/18/2016.
 */
public class ActiveProfileFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "ActiveProfileFragment";
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
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        menu.clear();
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_near_by, menu);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_near_by, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_safe_house:
                showNearBySafeHouses();
                break;
        }
        return true;
    }

    private void showNearBySafeHouses() {
        Intent intent = new Intent(mMainActivity, NearBySafeHouseActivity.class);
        startActivity(intent);
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

                break;
            case R.id.btn_alert:

                break;
            default:
                break;
        }
    }
}
