package com.opensource.app.idare.view.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.opensource.app.idare.R;
import com.opensource.app.idare.data.entities.NearBySafeHouseListEntity;
import com.opensource.app.idare.data.entities.NearBySafeHouseResultEntity;
import com.opensource.app.idare.service.handlers.NearBySafeHouseResponseHandler;
import com.opensource.app.idare.service.impl.ServiceFacadeImpl;
import com.opensource.app.idare.util.Utility;
import com.opensource.app.idare.util.log.Logger;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ajaiswal on 4/4/2016.
 */
public class NearBySafeHouseActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnMyLocationButtonClickListener {

    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 1000;
    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 2;
    protected final static String LOCATION_KEY = "location-key";
    protected final static String LAST_UPDATED_TIME_STRING_KEY = "last-updated-time-string-key";
    private static final String TAG = "NearBySafeHouseActivity";
    private static final float SMALLEST_DISPLACEMENT = 250.0f;

    protected LocationRequest mLocationRequest;
    protected GoogleApiClient mGoogleApiClient;
    protected Location mCurrentLocation;
    protected Location mLastLocation;

    protected String mLastUpdateTime;

    private GoogleMap googleMap;
    private String myLocation;
    private List<Marker> mMarkers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by_safe_house);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mLastUpdateTime = "";

        // Update values using data stored in the Bundle.
        updateValuesFromBundle(savedInstanceState);
        buildGoogleClientAPI();
    }

    private void updateValuesFromBundle(Bundle savedInstanceState) {
        Logger.i(TAG, "Updating values from bundle");
        if (savedInstanceState != null) {
            if (savedInstanceState.keySet().contains(LOCATION_KEY)) {
                mCurrentLocation = savedInstanceState.getParcelable(LOCATION_KEY);
            }

            if (savedInstanceState.keySet().contains(LAST_UPDATED_TIME_STRING_KEY)) {
                mLastUpdateTime = savedInstanceState.getString(LAST_UPDATED_TIME_STRING_KEY);
            }
        }
    }

    private synchronized void buildGoogleClientAPI() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        createLocationRequest();
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setSmallestDisplacement(SMALLEST_DISPLACEMENT);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Stop location updates to save battery, but don't disconnect the GoogleApiClient object.
        if (mGoogleApiClient.isConnected()) {
            stopLocationUpdates();
        }
    }

    protected void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
        getNearBySafeHouses();
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    private void getNearBySafeHouses() {
        ServiceFacadeImpl.getServiceFacade().getNearBySafeHouses(getString(R.string.google_map_api_key), myLocation, "2000", "shopping_mall|police|cafe", new NearBySafeHouseHandler());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        googleMap.setMyLocationEnabled(true);
        googleMap.setOnMyLocationButtonClickListener(this);
    }

    public void addMarkers(NearBySafeHouseListEntity nearBySafeHouses) {
        mMarkers = new ArrayList<>();
        for (NearBySafeHouseResultEntity entity : nearBySafeHouses.getNearBySafeHouseResultEntities()) {
            MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(entity.getGeometry().getLocation().getLatitude(), entity.getGeometry().getLocation().getLongitude())).title(entity.getName()).snippet(entity.getVicinity());
            mMarkers.add(googleMap.addMarker(markerOptions));
        }
        setBounds();
    }

    public void setBounds() {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Marker marker : mMarkers) {
            builder.include(marker.getPosition());
        }
        LatLngBounds bounds = builder.build();
        int padding = 50;
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        googleMap.animateCamera(cameraUpdate);
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = mCurrentLocation;
        mCurrentLocation = location;
        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
        if (mLastLocation != null && mLastLocation.distanceTo(mCurrentLocation) > SMALLEST_DISPLACEMENT) {
            getNearBySafeHouses();
        }
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putParcelable(LOCATION_KEY, mCurrentLocation);
        savedInstanceState.putString(LAST_UPDATED_TIME_STRING_KEY, mLastUpdateTime);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onConnected(Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mLastLocation = mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            myLocation = String.format("%s,%s", mLastLocation.getLatitude(), mLastLocation.getLongitude());
        } else {
            Toast.makeText(this, R.string.no_location_detected, Toast.LENGTH_LONG).show();
        }
        startLocationUpdates();
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

    @Override
    public boolean onMyLocationButtonClick() {
        mGoogleApiClient.connect();
        return true;
    }

    class NearBySafeHouseHandler implements NearBySafeHouseResponseHandler {

        @Override
        public void onSuccess(NearBySafeHouseListEntity nearBySafeHouses) {
            System.out.println("RESULT " + nearBySafeHouses.toString());
            googleMap.clear();
            addMarkers(nearBySafeHouses);
        }

        @Override
        public void onError(Exception exception) {
            exception.printStackTrace();
        }
    }
}
