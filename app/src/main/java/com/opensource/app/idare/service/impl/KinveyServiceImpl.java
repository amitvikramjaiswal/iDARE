package com.opensource.app.idare.service.impl;

import android.content.Context;
import android.widget.Toast;

import com.google.api.client.json.GenericJson;
import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.android.callback.KinveyUserCallback;
import com.kinvey.java.LinkedResources.LinkedGenericJson;
import com.kinvey.java.Query;
import com.kinvey.java.User;
import com.kinvey.java.core.KinveyClientCallback;
import com.kinvey.java.core.UploaderProgressListener;
import com.opensource.app.idare.service.KinveyService;
import com.opensource.app.idare.util.KinveyUtil;

import java.io.File;

/**
 * Created by ajaiswal on 4/28/2016.
 */
public class KinveyServiceImpl implements KinveyService {

    private Client client;

    public KinveyServiceImpl(final Context context) {
        client = KinveyUtil.getKinveyClient(context);
        if (!client.user().isUserLoggedIn()) {
            client.user().login(new KinveyUserCallback() {
                @Override
                public void onSuccess(User user) {
                    Toast.makeText(context, "LOGGED IN", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Throwable throwable) {
                    Toast.makeText(context, "NOT LOGGED IN", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void save(GenericJson genericJson, String pCollectionName, KinveyClientCallback<GenericJson> pCallback, Class pClass) {
        client.appData(pCollectionName, pClass).save(genericJson, pCallback);
    }

    @Override
    public void saveLinkedData(LinkedGenericJson genericJson, String pCollectionName, KinveyClientCallback<GenericJson> callback, UploaderProgressListener progressListener, Class pClass) {
        client.linkedData(pCollectionName, pClass).save(genericJson, callback, progressListener);
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

    @Override
    public void findAll(String pCollectionName, KinveyListCallback<GenericJson> callback, Class pClass) {
        client.appData(pCollectionName, pClass).get(callback);

    }

    @Override
    public void uploadPic(File file, UploaderProgressListener progressListener) {
        client.file().upload(file, progressListener);
    }
}
