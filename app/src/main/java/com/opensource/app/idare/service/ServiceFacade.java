package com.opensource.app.idare.service;

import android.content.Context;

import com.google.api.client.json.GenericJson;
import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.android.callback.KinveyPingCallback;
import com.kinvey.java.core.KinveyClientCallback;
import com.opensource.app.idare.data.entities.CoreUserEntity;
import com.opensource.app.idare.service.handlers.NearBySafeHouseResponseHandler;

/**
 * Created by ajaiswal on 4/6/2016.
 */
public interface ServiceFacade {

    void getNearBySafeHouses(String key, String location, String radius, String type, NearBySafeHouseResponseHandler responseHandler);

    void ping(Context context, KinveyPingCallback kinveyPingCallback);

    void save(Context pContext, GenericJson pGenericJson, String pCollectionName, KinveyClientCallback<GenericJson> pCallback, Class pClass);

    void findAll(String pCollectionName, KinveyListCallback<GenericJson> callback, Class pClass, Context pContext);
}
