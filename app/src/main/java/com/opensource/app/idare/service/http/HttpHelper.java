package com.opensource.app.idare.service.http;

import com.google.inject.ImplementedBy;

/**
 * Interface for the HTTP helper implementation.
 */
public interface HttpHelper {

    /**
     * Send a HTTP request.
     * 
     * @param request
     *            The request to send.
     * @return The response.
     */
    HttpHelperResponse sendRequest(HttpHelperRequest request);

    /**
     * Send a HTTP request asynchronously.
     * 
     * @param request
     * @param responseHandler
     *            The response handler.
     */
    void sendRequestAsync(HttpHelperRequest request, HttpHelperResponseHandler responseHandler);
}
