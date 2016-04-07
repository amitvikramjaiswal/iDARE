package com.opensource.app.idare.service.impl;

import com.opensource.app.idare.service.NearBySafeHouseService;
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
    private HttpHelper httpHelper;
    private NearBySafeHouseService nearBySafeHouseService;
    private static ServiceFacade serviceFacade;

    static {
        serviceFacade = new ServiceFacadeImpl();
    }

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

}
