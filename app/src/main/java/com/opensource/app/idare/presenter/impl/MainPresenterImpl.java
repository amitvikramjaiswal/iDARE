package com.opensource.app.idare.presenter.impl;

import com.opensource.app.idare.presenter.presenters.MainPresenter;
import com.opensource.app.idare.view.views.MainView;

/**
 * Created by ajaiswal on 3/15/2016.
 */
public class MainPresenterImpl implements MainPresenter {

    MainView mainView;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
    }
}