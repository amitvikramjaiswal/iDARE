package com.opensource.app.idare.presenter.presenters;

import android.support.v4.app.Fragment;

/**
 * Created by ajaiswal on 3/15/2016.
 */
public interface MainPresenter {

    void replaceFragment(Fragment fragment);

    void logout();
}
