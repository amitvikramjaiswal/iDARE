package com.opensource.app.idare.presenter.impl;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;

import com.google.api.client.json.GenericJson;
import com.google.gson.Gson;
import com.kinvey.java.LinkedResources.LinkedFile;
import com.kinvey.java.core.KinveyClientCallback;
import com.kinvey.java.core.MediaHttpUploader;
import com.kinvey.java.core.UploaderProgressListener;
import com.kinvey.java.model.FileMetaData;
import com.opensource.app.idare.application.IDareApp;
import com.opensource.app.idare.data.entities.IDareUser;
import com.opensource.app.idare.data.entities.UserContext;
import com.opensource.app.idare.presenter.presenters.EditProfilePresenter;
import com.opensource.app.idare.util.Utility;
import com.opensource.app.idare.util.Utils;
import com.opensource.app.idare.view.views.EditProfileView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by ajaiswal on 4/18/2016.
 */
public class EditProfilePresenterImpl extends BasePresenterImpl implements EditProfilePresenter {

    private EditProfileView editProfileView;

    public EditProfilePresenterImpl(EditProfileView editProfileView) {
        this.editProfileView = editProfileView;
        editProfileView.findViews();
        editProfileView.setValues();
        editProfileView.setListeners();
    }

    @Override
    public void saveProfile() {
        UserContext userContext = IDareApp.getUserContext();
        IDareUser iDareUser = new IDareUser();

        if (userContext.getFilePath() != null && userContext.getFilePath().isEmpty()) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            Bitmap image = Utils.decodeBitmap(IDareApp.getContext(), Uri.parse(userContext.getFilePath()), 960);
            image.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] bytes = stream.toByteArray();
            try {
                stream.close();
            } catch (IOException e) {
            }

            //create new ByteArrayInputStream from byte[] and associated with linked entity
            iDareUser.putFile(Utility.KINVEY_PROFILE_PIC, new LinkedFile(userContext.getName()));
            iDareUser.getFile(Utility.KINVEY_PROFILE_PIC).setInput(new ByteArrayInputStream(bytes));
        }

        if (userContext.getId() != null) {
            iDareUser.setId(userContext.getId());
        }
        iDareUser.setName(userContext.getName());
        iDareUser.setMobile(userContext.getMobile());
        iDareUser.setEmail(userContext.getEmail());
        iDareUser.setAlternate(userContext.getAlternateMobile());
        getServiceFacade().save(IDareApp.getContext(), iDareUser, Utility.KINVEY_IDARE_USER, new SaveProfileCallback(), IDareUser.class);
        getServiceFacade().uploadPic(new File(userContext.getFilePath()), new SaveProfileProgressListener(), IDareApp.getContext());
    }

    private class SaveProfileCallback implements KinveyClientCallback<GenericJson> {
        @Override
        public void onSuccess(GenericJson genericJson) {
            SharedPreferences.Editor editor = editProfileView.getPreferences().edit();
            editor.putString(Utility.KEY_USER_CONTEXT, new Gson().toJson(IDareApp.getUserContext()));
            editor.apply();
        }

        @Override
        public void onFailure(Throwable throwable) {

        }
    }

    private class SaveProfileProgressListener implements UploaderProgressListener {

        @Override
        public void progressChanged(MediaHttpUploader mediaHttpUploader) throws IOException {

        }

        @Override
        public void onSuccess(FileMetaData fileMetaData) {
             
        }

        @Override
        public void onFailure(Throwable throwable) {

        }
    }
}
