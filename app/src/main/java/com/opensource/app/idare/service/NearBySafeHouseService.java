package com.opensource.app.idare.service;

import com.opensource.app.idare.service.handlers.NearBySafeHouseResponseHandler;
import com.opensource.app.idare.service.http.HttpHelperRequest;
import com.opensource.app.idare.service.http.HttpHelperResponse;

/**
 * Created by ajaiswal on 4/5/2016.
 */
public interface NearBySafeHouseService {

    /**
     * @param key      Google API key
     * @param location User's current location
     * @param radius   radius of search
     * @param type     type to search
     * @return Gets a HTTP request that can be sent that requests nearby search results.
     */
    HttpHelperRequest getHttpRequest(String key, String location, String radius, String type);

    /**
     * Parses a response into the specified handler.
     *
     * @param response        The HTTP response to parse.
     * @param responseHandler The result handler.
     */
    void parseResponse(HttpHelperResponse response, NearBySafeHouseResponseHandler responseHandler);

}
