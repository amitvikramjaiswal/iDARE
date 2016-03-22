package com.opensource.app.idare.presenter.impl;

import android.widget.EditText;

import com.opensource.app.idare.R;
import com.opensource.app.idare.presenter.presenters.RegisterPresenter;
import com.opensource.app.idare.service.handlers.AlertDialogHandler;
import com.opensource.app.idare.util.Utils;
import com.opensource.app.idare.view.views.RegisterView;

/**
 * Created by ajaiswal on 3/21/2016.
 */
public class RegisterPresenterImpl implements RegisterPresenter {

    private RegisterView registerView;

    public RegisterPresenterImpl(RegisterView registerView) {
        this.registerView = registerView;
        registerView.findViews();
        registerView.setValues();
        registerView.setListeners();
    }

    @Override
    public void onSendVerificationClick(EditText etPhoneNumber) {
        String phoneNumber = etPhoneNumber.getText().toString();
        if (Utils.hasContent(phoneNumber) && phoneNumber.length() == 10) {
            registerView.showAlertDialog(registerView.getString(R.string.app_name), registerView.getString(R.string.verify_confirmation_message, phoneNumber), registerView.getString(R.string.btn_ok), registerView.getString(R.string.btn_edit), new AlertDialogHandler() {
                @Override
                public void onPositiveButtonClicked() {
                    registerView.onSendVerificationClick();
                }

                @Override
                public void onNegativeButtonClicked() {

                }
            });
        } else {
            registerView.shakeView(etPhoneNumber);
        }
    }

    @Override
    public void onVerifyClick(EditText etOtpCode) {
        String otp = etOtpCode.getText().toString();
        if (Utils.hasContent(otp) && otp.equalsIgnoreCase("123456")) {
            registerView.onVerifyClick();
        } else {
            registerView.shakeView(etOtpCode);
        }
    }
}