package com.opensource.app.idare.service.impl;

import android.content.Context;

import com.google.api.client.json.GenericJson;
import com.kinvey.android.AsyncAppData;
import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.java.Query;
import com.kinvey.java.core.KinveyClientCallback;
import com.opensource.app.idare.service.KinveyService;
import com.opensource.app.idare.util.KinveyUtil;

/**
 * Created by ajaiswal on 4/28/2016.
 */
public class KinveyServiceImpl implements KinveyService {

    private Client client;

    public KinveyServiceImpl(Context context) {
        client = KinveyUtil.getKinveyClient(context);
    }

    @Override
    public void save(GenericJson genericJson, String pCollectionName, KinveyClientCallback<GenericJson> pCallback, Class pClass) {
        AsyncAppData asyncAppData = client.appData(pCollectionName, pClass);
        asyncAppData.save(genericJson, pCallback);
    }

    @Override
    public void findById(String id, KinveyClientCallback<GenericJson> callback) {

    }

    @Override
    public void deleteById(String id, KinveyClientCallback<GenericJson> callback) {

    }

    @Override
    public void queryKinveyDB(Query query, KinveyListCallback<GenericJson> callback) {

    }
}
