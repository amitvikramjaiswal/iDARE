package com.opensource.app.idare.presenter.presenters;

import android.support.v4.app.Fragment;

import com.kinvey.android.callback.KinveyPingCallback;

/**
 * Created by ajaiswal on 3/15/2016.
 */
public interface MainPresenter extends BasePresenter {

    void replaceFragment(Fragment fragment);

    void logout();

    void ping(KinveyPingCallback kinveyPingCallback);
}
