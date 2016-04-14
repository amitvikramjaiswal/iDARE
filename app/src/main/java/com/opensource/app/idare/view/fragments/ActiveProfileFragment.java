package com.opensource.app.idare.view.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.opensource.app.idare.R;
import com.opensource.app.idare.util.Utility;
import com.opensource.app.idare.util.log.Logger;
import com.opensource.app.idare.view.activities.MainActivity;
import com.opensource.app.idare.view.activities.NearBySafeHouseActivity;

/**
 * Created by ajaiswal on 3/18/2016.
 */
public class ActiveProfileFragment extends BaseFragment implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "ActiveProfileFragment";
    private MainActivity mMainActivity;
    private Context mContext;
    private Button btnAlert;
    private Button btnCallNearestPoliceStation;

    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        mMainActivity = (MainActivity) getActivity();
        mContext = mMainActivity.getApplicationContext();
        setHasOptionsMenu(true);
        mMainActivity.getSupportActionBar().setTitle(mMainActivity.getStringArray(R.array.arr_nav_titles)[0]);
        buildGoogleClientAPI();

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
                if (mLastLocation != null)
                    showNearBySafeHouses();
                break;
        }
        return true;
    }

    private void showNearBySafeHouses() {
        Intent intent = new Intent(mMainActivity, NearBySafeHouseActivity.class);
        intent.putExtra(Utility.KEY_LAST_LOCATION, mLastLocation);
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

    private synchronized void buildGoogleClientAPI() {
        mGoogleApiClient = new GoogleApiClient.Builder(mContext)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnected(Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(mMainActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mMainActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Logger.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Logger.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + connectionResult.getErrorCode());
    }
}
