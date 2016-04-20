package com.opensource.app.idare.view.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.opensource.app.idare.R;
import com.opensource.app.idare.application.IDareApp;
import com.opensource.app.idare.data.entities.UserContext;
import com.opensource.app.idare.presenter.impl.EditProfilePresenterImpl;
import com.opensource.app.idare.presenter.presenters.EditProfilePresenter;
import com.opensource.app.idare.util.ImagePicker;
import com.opensource.app.idare.view.views.EditProfileView;

/**
 * Created by ajaiswal on 4/18/2016.
 */
public class EditProfileActivity extends BaseActivity implements EditProfileView, View.OnClickListener, TextWatcher {

    private static final String TAG = "EditProfileActivity";
    private EditProfilePresenter editProfilePresenter;

    private ImageView ivUserProfile;
    private EditText etName;
    private EditText etAlternateNumber;
    private EditText etEmail;
    private Button btnSaveProfile;

    private UserContext userContext;

    @Override
    protected void onBaseActivityCreate(Bundle savedInstanceState) {
        super.onBaseActivityCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        userContext = IDareApp.getUserContext();
        editProfilePresenter = new EditProfilePresenterImpl(this);
    }

    @Override
    public void findViews() {
        ivUserProfile = (ImageView) findViewById(R.id.iv_user_profile);
        etName = (EditText) findViewById(R.id.et_name);
        etAlternateNumber = (EditText) findViewById(R.id.et_alternate_number);
        etEmail = (EditText) findViewById(R.id.et_email);
        btnSaveProfile = (Button) findViewById(R.id.btn_save_profile);
    }

    @Override
    public void setValues() {
        if (userContext == null)
            return;
        if (userContext.getFilePath() != null)
            ivUserProfile.setImageURI(Uri.parse(userContext.getFilePath()));
        etName.setText(userContext.getName());
        etEmail.setText(userContext.getEmail());
        etAlternateNumber.setText(userContext.getAlternateMobile());
    }

    @Override
    public void setListeners() {
        ivUserProfile.setOnClickListener(this);
        btnSaveProfile.setOnClickListener(this);
        etName.addTextChangedListener(this);
        etEmail.addTextChangedListener(this);
        etAlternateNumber.addTextChangedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save_profile:
                onSaveBtnClick();
                break;
            case R.id.iv_user_profile:
                onImageClick();
                break;
            default:
                break;
        }
    }

    private void onImageClick() {
        Intent intent = new Intent(EditProfileActivity.this, ProfilePicActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSaveBtnClick() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String alternate = etAlternateNumber.getText().toString().trim();
        if (userContext == null)
            userContext = new UserContext();
        userContext.setName(name);
        userContext.setEmail(email);
        userContext.setAlternateMobile(alternate);
        editProfilePresenter.saveProfile();
    }

    private void checkFieldsForEmptyValues() {
        String strName = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String alternate = etAlternateNumber.getText().toString().trim();
        if (strName == null || strName.isEmpty()) {
            btnSaveProfile.setEnabled(false);
        } else {
            if (IDareApp.getUserContext() != null && (strName.equalsIgnoreCase(IDareApp.getUserContext().getName()) && email != null && email.equalsIgnoreCase(IDareApp.getUserContext().getEmail()) && alternate != null && alternate.equalsIgnoreCase(IDareApp.getUserContext().getAlternateMobile()))) {
                btnSaveProfile.setEnabled(false);
            } else {
                btnSaveProfile.setEnabled(true);
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        checkFieldsForEmptyValues();
    }
}
