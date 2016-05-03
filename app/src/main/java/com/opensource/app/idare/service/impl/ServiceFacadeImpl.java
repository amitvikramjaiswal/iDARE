package com.opensource.app.idare.service.impl;

import android.content.Context;

import com.google.api.client.json.GenericJson;
import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.android.callback.KinveyPingCallback;
import com.kinvey.java.core.KinveyClientCallback;
import com.opensource.app.idare.data.entities.CoreUserEntity;
import com.opensource.app.idare.service.KinveyService;
import com.opensource.app.idare.service.NearBySafeHouseService;
import com.opensource.app.idare.service.PingService;
import com.opensource.app.idare.service.ServiceFacade;
import com.opensource.app.idare.service.handlers.NearBySafeHouseResponseHandler;
import com.opensource.app.idare.service.http.HttpHelper;
import com.opensource.app.idare.service.http.HttpHelperRequest;
import com.opensource.app.idare.service.http.HttpHelperResponse;
import com.opensource.app.idare.service.http.HttpHelperResponseHandler;
import com.opensource.app.idare.service.http.impl.HttpHelperImpl;

/**
 * Created by ajaiswal on 4/6/2016.
 */
public class ServiceFacadeImpl implements ServiceFacade {
    private static ServiceFacade serviceFacade;

    static {
        serviceFacade = new ServiceFacadeImpl();
    }

    private HttpHelper httpHelper;
    private NearBySafeHouseService nearBySafeHouseService;
    private PingService pingService;
    private KinveyService kinveyService;

    private ServiceFacadeImpl() {
        httpHelper = HttpHelperImpl.getHttpHelper();
        nearBySafeHouseService = new NearBySafeHouseServiceImpl();
    }

    public static ServiceFacade getServiceFacade() {
        return serviceFacade;
    }

    @Override
    public void getNearBySafeHouses(String key, String location, String radius, String type, final NearBySafeHouseResponseHandler responseHandler) {
        HttpHelperRequest request = nearBySafeHouseService.getHttpRequest(key, location, radius, type);
        httpHelper.sendRequestAsync(request, new HttpHelperResponseHandler() {
            @Override
            public void onRequestComplete(HttpHelperResponse response) {
                nearBySafeHouseService.parseResponse(response, responseHandler);
            }
        });
    }

    @Override
    public void ping(Context context, KinveyPingCallback kinveyPingCallback) {
        pingService = pingService == null ? new PingServiceImpl(context) : pingService;
        pingService.ping(kinveyPingCallback);
    }

    @Override
    public void save(Context pContext, GenericJson pGenericJson, String pCollectionName, KinveyClientCallback<GenericJson> pCallback, Class pClass) {
        kinveyService = kinveyService == null ? new KinveyServiceImpl(pContext) : kinveyService;
        kinveyService.save(pGenericJson, pCollectionName, pCallback, pClass);
    }

    @Override
    public void findAll(String pCollectionName, KinveyListCallback<GenericJson> callback, Class pClass, Context pContext) {
        kinveyService = kinveyService == null ? new KinveyServiceImpl(pContext) : kinveyService;
        kinveyService.findAll(pCollectionName, callback, pClass);
    }
}
