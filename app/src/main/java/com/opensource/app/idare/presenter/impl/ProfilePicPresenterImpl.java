package com.opensource.app.idare.presenter.impl;

import com.opensource.app.idare.presenter.presenters.ProfilePicPresenter;
import com.opensource.app.idare.view.views.ProfilePicView;

/**
 * Created by ajaiswal on 4/20/2016.
 */
public class ProfilePicPresenterImpl implements ProfilePicPresenter {

    private static final String TAG = "ProfilePicPresenterImpl";
    private ProfilePicView profilePicView;

    public ProfilePicPresenterImpl(ProfilePicView profilePicView) {
        this.profilePicView = profilePicView;
        profilePicView.findViews();
        profilePicView.setValues();
        profilePicView.addListeners();
    }
}
