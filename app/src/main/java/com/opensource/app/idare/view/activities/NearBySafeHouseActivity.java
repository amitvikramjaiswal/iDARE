package com.opensource.app.idare.view.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.opensource.app.idare.R;
import com.opensource.app.idare.service.handlers.NearBySafeHouseResponseHandler;

/**
 * Created by ajaiswal on 4/4/2016.
 */
public class NearBySafeHouseActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by_safe_house);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setMyLocationEnabled(true);
    }

    class NearBySafeHouseHandler implements NearBySafeHouseResponseHandler {

        @Override
        public void onSuccess() {

        }

        @Override
        public void onError() {

        }
    }
}
