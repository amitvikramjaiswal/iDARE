package com.opensource.app.idare.service.impl;

import android.net.Uri;

import com.opensource.app.idare.R;
import com.opensource.app.idare.application.IDareApp;
import com.opensource.app.idare.service.NearBySafeHouseService;
import com.opensource.app.idare.service.handlers.NearBySafeHouseResponseHandler;
import com.opensource.app.idare.service.http.HttpHelperRequest;
import com.opensource.app.idare.service.http.HttpHelperResponse;
import com.opensource.app.idare.util.Utility;

/**
 * Created by ajaiswal on 4/5/2016.
 */
public class NearBySafeHouseServiceImpl implements NearBySafeHouseService {

    private static final String TAG = "NearBySafeHouseServiceImpl";

    @Override
    public HttpHelperRequest getHttpRequest(String key, String location, String radius, String type) {
        HttpHelperRequest request = new HttpHelperRequest();
        Uri uri = Uri.parse(Utility.NEAR_BY_SEARCH_BASE_URL);
        Uri.Builder builder = uri.buildUpon();
        builder.appendQueryParameter("key", key);
        builder.appendQueryParameter("location", location);
        builder.appendQueryParameter("radius", radius);
        builder.appendQueryParameter("type", type);
        request.setUrl(builder.build().toString());
        request.setRequestMethod(HttpHelperRequest.HTTP_GET);
        return null;
    }

    @Override
    public void parseResponse(HttpHelperResponse response, NearBySafeHouseResponseHandler responseHandler) {

    }
}
