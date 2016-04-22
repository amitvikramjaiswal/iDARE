package com.opensource.app.idare.presenter.impl;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.opensource.app.idare.application.IDareApp;
import com.opensource.app.idare.presenter.presenters.EditProfilePresenter;
import com.opensource.app.idare.util.Utility;
import com.opensource.app.idare.view.views.EditProfileView;

/**
 * Created by ajaiswal on 4/18/2016.
 */
public class EditProfilePresenterImpl implements EditProfilePresenter {

    private EditProfileView editProfileView;

    public EditProfilePresenterImpl(EditProfileView editProfileView) {
        this.editProfileView = editProfileView;
        editProfileView.findViews();
        editProfileView.setValues();
        editProfileView.setListeners();
    }

    @Override
    public void saveProfile() {
        SharedPreferences.Editor editor = editProfileView.getPreferences().edit();
        editor.putString(Utility.KEY_USER_CONTEXT, new Gson().toJson(IDareApp.getUserContext()));
        editor.apply();
    }
}
