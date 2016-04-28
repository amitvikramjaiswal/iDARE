package com.opensource.app.idare.view.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.opensource.app.idare.R;
import com.opensource.app.idare.application.IDareApp;
import com.opensource.app.idare.presenter.impl.ProfilePicPresenterImpl;
import com.opensource.app.idare.presenter.presenters.BasePresenter;
import com.opensource.app.idare.presenter.presenters.ProfilePicPresenter;
import com.opensource.app.idare.util.ImagePicker;
import com.opensource.app.idare.view.views.ProfilePicView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by ajaiswal on 4/20/2016.
 */
public class ProfilePicActivity extends BaseActivity implements ProfilePicView, View.OnClickListener {

    private static final String TAG = "ProfilePicActivity";
    private static final String PROFILE_PIC_PNG = "profile_pic.png";
    private static final int HAS_SAVED = 888;
    private static final int HAS_NOT_SAVED = 777;
    private ProfilePicPresenter profilePicPresenter;

    private ImageView ivUserProfilePic;

    @Override
    protected void onBaseActivityCreate(Bundle savedInstanceState) {
        super.onBaseActivityCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_pic);
        setTitle(R.string.profile_pic);
        profilePicPresenter = new ProfilePicPresenterImpl(this);
    }

    @Override
    public BasePresenter getPresenter() {
        return profilePicPresenter;
    }

    @Override
    public void findViews() {
        ivUserProfilePic = (ImageView) findViewById(R.id.iv_user_profile);
    }

    @Override
    public void setValues() {
        String uri = IDareApp.getUserContext().getFilePath();
        if (uri != null && !uri.isEmpty()) {
            ivUserProfilePic.setImageBitmap(null);
            ivUserProfilePic.setImageURI(Uri.parse(uri));
        }
    }

    @Override
    public void addListeners() {
        ivUserProfilePic.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_user_profile:
                onImageClick();
                break;
            default:
                break;
        }
    }

    private void onImageClick() {
        startActivityForResult(ImagePicker.getPickImageIntent(this), 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = ImagePicker.getImageFromResult(this, resultCode, data);
        if (bitmap == null) {
            setResult(HAS_NOT_SAVED);
            return;
        }
        File file = savePic(bitmap);
        if (file != null) {
            IDareApp.getUserContext().setFilePath(file.getPath());
            setValues();
            setResult(HAS_SAVED);
        }
    }

    private File savePic(Bitmap bitmap) {
        File file = new File(getCacheDir(), PROFILE_PIC_PNG);
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
            return file;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
