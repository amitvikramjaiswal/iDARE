package com.opensource.app.idare.service.impl;

import android.content.Context;

import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyPingCallback;
import com.opensource.app.idare.service.PingService;
import com.opensource.app.idare.util.KinveyUtil;

/**
 * Created by ajaiswal on 4/27/2016.
 */
public class PingServiceImpl implements PingService {

    private Client client;

    public PingServiceImpl(Context context) {
        client = KinveyUtil.getKinveyClient(context);
    }

    @Override
    public void ping(KinveyPingCallback kinveyPingCallback) {
        client.ping(kinveyPingCallback);
    }
}
