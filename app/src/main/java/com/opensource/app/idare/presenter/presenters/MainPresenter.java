package com.opensource.app.idare.presenter.presenters;

import android.support.v4.app.Fragment;

import com.google.api.client.json.GenericJson;
import com.kinvey.android.callback.KinveyPingCallback;
import com.kinvey.java.core.KinveyClientCallback;

/**
 * Created by ajaiswal on 3/15/2016.
 */
public interface MainPresenter extends BasePresenter {

    void replaceFragment(Fragment fragment);

    void logout();

    void ping(KinveyPingCallback kinveyPingCallback);

    void save(GenericJson pGenericJson, String pCollection, KinveyClientCallback<GenericJson> pCallback, Class pClass);
}
