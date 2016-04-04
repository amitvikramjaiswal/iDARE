package com.opensource.app.idare.presenter.presenters;

import android.widget.EditText;

/**
 * Created by ajaiswal on 3/21/2016.
 */
public interface RegisterPresenter {

    void onSendVerificationClick(EditText phoneNumber);

    void onVerifyClick(EditText otp);

    void saveUserData();
    
}
