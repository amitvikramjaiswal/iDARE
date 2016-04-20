package com.opensource.app.idare.service.http.impl;

import android.os.Handler;
import android.os.Looper;
import android.util.Base64;

import com.opensource.app.idare.service.http.HttpHelper;
import com.opensource.app.idare.service.http.HttpHelperRequest;
import com.opensource.app.idare.service.http.HttpHelperResponse;
import com.opensource.app.idare.service.http.HttpHelperResponseHandler;
import com.opensource.app.idare.util.log.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

/**
 * HttpHelper implementation.
 */
public class HttpHelperImpl implements HttpHelper {

    private static final String LOG_TAG = "HttpHelperImpl";
    private Handler mUiThreadHandler;
    private static HttpHelper httpHelper;

    static {
        httpHelper = new HttpHelperImpl();
    }

    private HttpHelperImpl() {
        setHandler(new Handler(Looper.getMainLooper()));
    }

    public static HttpHelper getHttpHelper() {
        return httpHelper;
    }

    void setHandler(Handler handler) {
        mUiThreadHandler = handler;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendRequestAsync(final HttpHelperRequest request, final HttpHelperResponseHandler responseHandler) {
        Thread t = new Thread() {
            @Override
            public void run() {
                final HttpHelperResponse response = sendRequest(request);
                mUiThreadHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        responseHandler.onRequestComplete(response);
                    }
                });
            }
        };
        t.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpHelperResponse sendRequest(HttpHelperRequest request) {
        HttpHelperResponse response = new HttpHelperResponse();
        HttpURLConnection connection = null;
        try {
            Logger.d(LOG_TAG, "Creating connection to url=" + request.getUrl() + " proxy=" + request.getProxy());
            Proxy proxy = request.getProxy();
            if (proxy != null) {
                connection = (HttpURLConnection) new URL(request.getUrl()).openConnection(proxy);
            } else {
                connection = (HttpURLConnection) new URL(request.getUrl()).openConnection();
            }
            setRequestMethod(request, connection);
            setRequestHeaders(request, connection);
            setRequestCredentials(request, connection);
            setTimeouts(request, connection);
            setOptions(request, connection);
            connection.connect();
            writeData(request, connection);
            readResponse(connection, response);
        } catch (IOException e) {
            Logger.d(LOG_TAG, "Exception occurred in HTTP request", e);
            response.setIsError(true);
            response.setException(e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return response;
    }

    /**
     * Sets basic auth credentials on the request
     *
     * @param request    The request.
     * @param connection The HTTP connection.
     */
    private void setRequestCredentials(HttpHelperRequest request, HttpURLConnection connection) {
        String username = request.getBasicAuthUsername();
        String password = request.getBasicAuthPassword();

        if (username != null || password != null) {
            connection.addRequestProperty("Authorization", "Basic " + Base64.encodeToString((username + ":" + password).getBytes(), 0));
        }
    }

    /**
     * Reads the response from the input or error stream and saves it to the
     * response object.
     *
     * @param connection The HTTP connection.
     * @param response   The response object.
     * @throws java.io.IOException If an error occurs.
     */
    void readResponse(HttpURLConnection connection, HttpHelperResponse response) throws IOException {
        InputStream inputStream = null;
        if (connection.getDoInput()) {
            try {
                inputStream = connection.getInputStream();
            } catch (IOException e) {
                inputStream = connection.getErrorStream();
                response.setIsError(true);
            }
        }
        int responseCode = connection.getResponseCode();
        response.setResponseCode(responseCode);
        response.setResponseHeaders(connection.getHeaderFields());

        // Get input stream
        if (responseCode >= HttpURLConnection.HTTP_BAD_REQUEST && inputStream == null) {
            inputStream = connection.getErrorStream();
            response.setIsError(true);
        }

        if (inputStream == null) {
            return;
        }

        // Get data from input stream to bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len;
        byte[] buffer = new byte[4096];
        while (-1 != (len = inputStream.read(buffer))) {
            baos.write(buffer, 0, len);
        }

        response.setResponse(baos.toByteArray());

        inputStream.close();
    }

    /**
     * Writes data to the output stream of the request.
     *
     * @param request    The request object.
     * @param connection The HTTP connection.
     * @throws java.io.IOException If an error occurs.
     */
    void writeData(HttpHelperRequest request, HttpURLConnection connection) throws IOException {
        if (request.getContent() != null && request.getContent().length > 0) {
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(request.getContent());
            outputStream.close();
        }
    }

    /**
     * Sets various options on the connection.
     *
     * @param request    The request object.
     * @param connection The HTTP connection.
     */
    void setOptions(HttpHelperRequest request, HttpURLConnection connection) {
        connection.setInstanceFollowRedirects(request.isInstanceFollowsRedirects());
    }

    /**
     * Sets the timeouts on the connection.
     *
     * @param request    The request object.
     * @param connection The HTTP connection.
     */
    void setTimeouts(HttpHelperRequest request, HttpURLConnection connection) {
        connection.setConnectTimeout(request.getConnectionTimeout());
        connection.setReadTimeout(request.getReadTimeout());
    }

    /**
     * Sets the request headers on the connection.
     *
     * @param request    The request object.
     * @param connection The HTTP connection.
     */
    void setRequestHeaders(HttpHelperRequest request, HttpURLConnection connection) {
        Map<String, String> headers = request.getAdditionalHeaders();
        if (headers != null) {
            for (final Entry<String, String> header : headers.entrySet()) {
                connection.addRequestProperty(header.getKey(), header.getValue());
            }
        }
    }

    /**
     * Sets the request method on the connection.
     *
     * @param request    The request object.
     * @param connection The HTTP connection.
     * @throws java.net.ProtocolException If an error occurs.
     */
    void setRequestMethod(HttpHelperRequest request, HttpURLConnection connection) throws ProtocolException {
        connection.setRequestMethod(request.getRequestMethod());
        connection.setDoInput(!request.getRequestMethod().equals(HttpHelperRequest.HTTP_HEAD));
        connection.setDoOutput(request.getContent() != null && request.getContent().length > 0);
    }
}
