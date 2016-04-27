package com.opensource.app.idare.service;

import android.content.Context;

import com.kinvey.android.callback.KinveyPingCallback;
import com.opensource.app.idare.service.handlers.NearBySafeHouseResponseHandler;

/**
 * Created by ajaiswal on 4/6/2016.
 */
public interface ServiceFacade {
    void getNearBySafeHouses(String key, String location, String radius, String type, NearBySafeHouseResponseHandler responseHandler);
    void ping(Context context, KinveyPingCallback kinveyPingCallback);
}
