package com.opensource.app.idare.service.http;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Represents a response for an HTTP request.
 */
public class HttpHelperResponse {

    private boolean mIsError;
    private int mResponseCode;
    private byte[] mResponse;
    private Exception mException;
    private Map<String, List<String>> mResponseHeaders;

    /**
     * @return Whether this response is an error response.
     */
    public boolean isError() {
        return mIsError;
    }

    /**
     * @return The HTTP response code.
     */
    public int getResponseCode() {
        return mResponseCode;
    }

    /**
     * @return The response as a string.
     */
    public String getResponseString() {
        try {
            return new String(mResponse, "UTF-8");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @return If an error occurred, the exception that caused this error if
     *         applicable.
     */
    public Exception getException() {
        return mException;
    }

    /**
     * Sets whether this reply is an error reply.
     */
    public void setIsError(boolean isError) {
        mIsError = isError;
    }

    /**
     * Sets the HTTP response code.
     */
    public void setResponseCode(int responseCode) {
        mResponseCode = responseCode;
    }

    public byte[] getResponse() {
        return mResponse;
    }

    public void setResponse(byte[] response) {
        mResponse = response;
    }

    /**
     * Sets the exception for this response.
     */
    public void setException(Exception exception) {
        mException = exception;
    }

    /**
     * @return The headers for this response.
     */
    public Map<String, List<String>> getResponseHeaders() {
        return mResponseHeaders;
    }

    /**
     * Sets the response headers.
     */
    public void setResponseHeaders(Map<String, List<String>> responseHeaders) {
        mResponseHeaders = responseHeaders;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((mException == null) ? 0 : mException.hashCode());
        result = prime * result + (mIsError ? 1231 : 1237);
        result = prime * result + Arrays.hashCode(mResponse);
        result = prime * result + mResponseCode;
        result = prime * result + ((mResponseHeaders == null) ? 0 : mResponseHeaders.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        HttpHelperResponse other = (HttpHelperResponse) obj;
        if (mException == null) {
            if (other.mException != null) {
                return false;
            }
        } else if (!mException.equals(other.mException)) {
            return false;
        }
        if (mIsError != other.mIsError) {
            return false;
        }
        if (!Arrays.equals(mResponse, other.mResponse)) {
            return false;
        }
        if (mResponseCode != other.mResponseCode) {
            return false;
        }
        if (mResponseHeaders == null) {
            if (other.mResponseHeaders != null) {
                return false;
            }
        } else if (!mResponseHeaders.equals(other.mResponseHeaders)) {
            return false;
        }
        return true;
    }
}
