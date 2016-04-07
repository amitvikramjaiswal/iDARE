package com.opensource.app.idare.view.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ajaiswal on 4/4/2016.
 */
public class NearBySafeHouseActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private boolean isMapReady;
    private List<Marker> mMarkers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by_safe_house);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        getNearBySafeHouses();
    }

    private void getNearBySafeHouses() {
        ServiceFacadeImpl.getServiceFacade().getNearBySafeHouses(getString(R.string.google_map_api_key), "12.935065,77.611050", "2000", "shopping_mall|police|cafe", new NearBySafeHouseHandler());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.isMapReady = true;
        googleMap.setMyLocationEnabled(true);
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

    class NearBySafeHouseHandler implements NearBySafeHouseResponseHandler {

        @Override
        public void onSuccess(NearBySafeHouseListEntity nearBySafeHouses) {
            System.out.println("RESULT " + nearBySafeHouses.toString());
            addMarkers(nearBySafeHouses);
        }

        @Override
        public void onError(Exception exception) {
            exception.printStackTrace();
        }
    }
}
