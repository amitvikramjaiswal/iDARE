package com.opensource.app.idare.service;

import com.google.api.client.json.GenericJson;
import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.java.Query;
import com.kinvey.java.core.KinveyClientCallback;
import com.kinvey.java.core.UploaderProgressListener;
import com.opensource.app.idare.data.entities.CoreUserEntity;

import java.io.File;

/**
 * Created by ajaiswal on 4/28/2016.
 */
public interface KinveyService {

    void save(GenericJson genericJson, String pCollectionName, KinveyClientCallback<GenericJson> pCallback, Class pClass);

    void findById(String id, KinveyClientCallback<GenericJson> callback);

    void deleteById(String id, KinveyClientCallback<GenericJson> callback);

    void queryKinveyDB(Query query, KinveyListCallback<GenericJson> callback);

    void findAll(String pCollectionName, KinveyListCallback<GenericJson> callback, Class pClass);

    void uploadPic(File file, UploaderProgressListener progressListener);
}
