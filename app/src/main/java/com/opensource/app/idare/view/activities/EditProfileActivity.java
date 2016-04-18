package com.opensource.app.idare.view.activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.opensource.app.idare.R;
import com.opensource.app.idare.presenter.impl.EditProfilePresenterImpl;
import com.opensource.app.idare.presenter.presenters.EditProfilePresenter;
import com.opensource.app.idare.view.views.EditProfileView;

/**
 * Created by ajaiswal on 4/18/2016.
 */
public class EditProfileActivity extends BaseActivity implements EditProfileView {

    private static final String TAG = "EditProfileActivity";
    private EditProfilePresenter editProfilePresenter;

    private ImageView ivUserProfile;
    private EditText etName;
    private EditText etAlternateNumber;
    private EditText etEmail;

    @Override
    protected void onBaseActivityCreate(Bundle savedInstanceState) {
        super.onBaseActivityCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        editProfilePresenter = new EditProfilePresenterImpl();
    }
}
