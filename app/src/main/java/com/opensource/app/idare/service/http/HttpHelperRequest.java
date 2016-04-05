package com.opensource.app.idare.service.http;

import java.net.Proxy;
import java.util.Arrays;
import java.util.Map;

/**
 * Represents a HTTP helper request.
 */
public class HttpHelperRequest {

    public static final String HTTP_GET = "GET";
    public static final String HTTP_POST = "POST";
    public static final String HTTP_DELETE = "DELETE";
    public static final String HTTP_PUT = "PUT";
    public static final String HTTP_HEAD = "HEAD";
    public static final int DEFAULT_CONNECTION_TIMEOUT = 15000; // 15sec default
                                                                // timeout
    public static final int DEAFULT_READ_TIMEOUT = 15000; // 15sec default
                                                          // timeout

    private byte[] mContent = null;
    private String mUrl = null;
    private Map<String, String> mAdditionalHeaders = null;
    private String mRequestMethod = HTTP_GET;
    private int mConnectionTimeout = DEFAULT_CONNECTION_TIMEOUT; // default
    private int mReadTimeout = DEAFULT_READ_TIMEOUT; // default
    private boolean mInstanceFollowsRedirects = true;
    private Proxy mProxy;
    private String mBasicPassword;
    private String mBasicUsername;
    
    /**
     * Sets the proxy
     */
    public void setProxy(Proxy proxy) {
        mProxy = proxy;
    }
    
    /**
     * Gets the proxy
     */
    public Proxy getProxy() {
        return mProxy;
    }
    
    /**
     * Sets the basic auth username and password
     */
    public void setBasicAuthCredentials(String username, String password) {
        mBasicUsername = username;
        mBasicPassword = password;
    }
    
    /**
     * Gets the basic auth username
     */
    public String getBasicAuthUsername() {
        return mBasicUsername;
    }
    
    /**
     * Gets the basic auth password
     */
    public String getBasicAuthPassword() {
        return mBasicPassword;
    }

    /**
     * Sets the content string.
     */
    public void setContent(byte[] content) {
        mContent = content;
    }

    /**
     * @param url
     *            The URL to set for this request.
     */
    public void setUrl(String url) {
        mUrl = url;
    }

    /**
     * @param additionalHeaders
     *            The additional headers to set for this request.
     */
    public void setAdditionalHeaders(Map<String, String> additionalHeaders) {
        mAdditionalHeaders = additionalHeaders;
    }

    /**
     * @param requestMethod
     *            The request method for this request.
     */
    public void setRequestMethod(String requestMethod) {
        mRequestMethod = requestMethod;
    }

    /**
     * @return The byte content for this request.
     */
    public byte[] getContent() {
        return mContent;
    }

    /**
     * @return The URL for this request
     */
    public String getUrl() {
        return mUrl;
    }

    /**
     * @return The additional headers for this request.
     */
    public Map<String, String> getAdditionalHeaders() {
        return mAdditionalHeaders;
    }

    /**
     * @return The request method for this request.
     */
    public String getRequestMethod() {
        return mRequestMethod;
    }

    /**
     * @return The connection timeout for this request.
     */
    public int getConnectionTimeout() {
        return mConnectionTimeout;
    }

    /**
     * @param connectionTimeout
     *            The connection timeout to set for this request.
     */
    public void setConnectionTimeout(int connectionTimeout) {
        mConnectionTimeout = connectionTimeout;
    }

    /**
     * @return The read timeout for this request.
     */
    public int getReadTimeout() {
        return mReadTimeout;
    }

    /**
     * @return Whether this request should follow redirects.
     */
    public boolean isInstanceFollowsRedirects() {
        return mInstanceFollowsRedirects;
    }

    /**
     * @param readTimeout
     *            The read timeout to set for this request.
     */
    public void setReadTimeout(int readTimeout) {
        mReadTimeout = readTimeout;
    }

    /**
     * @param instanceFollowsRedirects
     *            Whether this request should follow redirects.
     */
    public void setInstanceFollowsRedirects(boolean instanceFollowsRedirects) {
        mInstanceFollowsRedirects = instanceFollowsRedirects;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((mAdditionalHeaders == null) ? 0 : mAdditionalHeaders.hashCode());
        result = prime * result + ((mBasicPassword == null) ? 0 : mBasicPassword.hashCode());
        result = prime * result + ((mBasicUsername == null) ? 0 : mBasicUsername.hashCode());
        result = prime * result + mConnectionTimeout;
        result = prime * result + Arrays.hashCode(mContent);
        result = prime * result + (mInstanceFollowsRedirects ? 1231 : 1237);
        result = prime * result + ((mProxy == null) ? 0 : mProxy.hashCode());
        result = prime * result + mReadTimeout;
        result = prime * result + ((mRequestMethod == null) ? 0 : mRequestMethod.hashCode());
        result = prime * result + ((mUrl == null) ? 0 : mUrl.hashCode());
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
        HttpHelperRequest other = (HttpHelperRequest) obj;
        if (mAdditionalHeaders == null) {
            if (other.mAdditionalHeaders != null) {
                return false;
            }
        } else if (!mAdditionalHeaders.equals(other.mAdditionalHeaders)) {
            return false;
        }
        if (mBasicPassword == null) {
            if (other.mBasicPassword != null) {
                return false;
            }
        } else if (!mBasicPassword.equals(other.mBasicPassword)) {
            return false;
        }
        if (mBasicUsername == null) {
            if (other.mBasicUsername != null) {
                return false;
            }
        } else if (!mBasicUsername.equals(other.mBasicUsername)) {
            return false;
        }
        if (mConnectionTimeout != other.mConnectionTimeout) {
            return false;
        }
        if (!Arrays.equals(mContent, other.mContent)) {
            return false;
        }
        if (mInstanceFollowsRedirects != other.mInstanceFollowsRedirects) {
            return false;
        }
        if (mProxy == null) {
            if (other.mProxy != null) {
                return false;
            }
        } else if (!mProxy.equals(other.mProxy)) {
            return false;
        }
        if (mReadTimeout != other.mReadTimeout) {
            return false;
        }
        if (mRequestMethod == null) {
            if (other.mRequestMethod != null) {
                return false;
            }
        } else if (!mRequestMethod.equals(other.mRequestMethod)) {
            return false;
        }
        if (mUrl == null) {
            if (other.mUrl != null) {
                return false;
            }
        } else if (!mUrl.equals(other.mUrl)) {
            return false;
        }
        return true;
    }
}
