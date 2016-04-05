package com.opensource.app.idare.service.http;

public interface HttpHelperResponseHandler {

    /**
     * Fires when the request has been completed.
     * 
     * @param response The response.
     */
    void onRequestComplete(HttpHelperResponse response);
}
