package com.opensource.app.idare.service;

import com.kinvey.android.callback.KinveyPingCallback;

/**
 * Created by ajaiswal on 4/27/2016.
 */
public interface PingService {

    void ping(KinveyPingCallback kinveyPingCallback);

}
