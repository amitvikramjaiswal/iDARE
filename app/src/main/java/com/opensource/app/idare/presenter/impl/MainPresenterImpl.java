package com.opensource.app.idare.presenter.impl;

import android.support.v4.app.Fragment;

import com.google.api.client.json.GenericJson;
import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.android.callback.KinveyPingCallback;
import com.kinvey.java.core.KinveyClientCallback;
import com.opensource.app.idare.presenter.presenters.MainPresenter;
import com.opensource.app.idare.view.views.MainView;

/**
 * Created by ajaiswal on 3/15/2016.
 */
public class MainPresenterImpl extends BasePresenterImpl implements MainPresenter {

    MainView mainView;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        this.mainView.findViews();
        this.mainView.addListeners();
    }


    @Override
    public void replaceFragment(Fragment fragment) {
        mainView.replaceFragment(fragment);
    }

    @Override
    public void logout() {
        mainView.getPreferences().edit().clear().commit();
        mainView.relaunch();
    }

    @Override
    public void ping(KinveyPingCallback kinveyPingCallback) {
        getServiceFacade().ping(mainView.getContext(), kinveyPingCallback);
    }

    public void save(GenericJson pGenericJson, String pCollection, KinveyClientCallback<GenericJson> pCallback, Class pClass) {
        getServiceFacade().save(mainView.getContext(), pGenericJson, pCollection, pCallback, pClass);
    }

    @Override
    public void findAll(String pCollectionName, KinveyListCallback<GenericJson> callback, Class pClass) {
        getServiceFacade().findAll(pCollectionName, callback, pClass, mainView.getContext());
    }
}
