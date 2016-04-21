package com.opensource.app.idare.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.opensource.app.idare.R;
import com.opensource.app.idare.presenter.impl.RegisterPresenterImpl;
import com.opensource.app.idare.presenter.presenters.RegisterPresenter;
import com.opensource.app.idare.util.Utility;
import com.opensource.app.idare.util.log.Logger;
import com.opensource.app.idare.view.views.RegisterView;

/**
 * Created by ajaiswal on 3/21/2016.
 */
public class RegisterActivity extends BaseActivity implements RegisterView, View.OnClickListener, TextView.OnEditorActionListener {

    private static final String TAG = "RegisterActivity";
    private Button btnVerify;
    private Button btnSendVerification;
    private EditText etPhoneNumber;
    private EditText etOtpCode;
    private LinearLayout llEnterNumber;
    private LinearLayout llEnterOtp;

    private RegisterPresenter registerPresenter;

    @Override
    protected void onBaseActivityCreate(Bundle savedInstanceState) {
        super.onBaseActivityCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerPresenter = new RegisterPresenterImpl(this);
    }

    @Override
    public void findViews() {
        btnSendVerification = (Button) findViewById(R.id.btn_send_verification);
        btnVerify = (Button) findViewById(R.id.btn_verify);
        etPhoneNumber = (EditText) findViewById(R.id.et_phone_number);
        etOtpCode = (EditText) findViewById(R.id.et_otp_code);
        llEnterNumber = (LinearLayout) findViewById(R.id.ll_enter_number);
        llEnterOtp = (LinearLayout) findViewById(R.id.ll_enter_otp);
    }

    @Override
    public void setValues() {
        llEnterNumber.setVisibility(View.VISIBLE);
        llEnterOtp.setVisibility(View.GONE);
    }

    @Override
    public void setListeners() {
        btnSendVerification.setOnClickListener(this);
        btnVerify.setOnClickListener(this);
        etPhoneNumber.setOnEditorActionListener(this);
        etOtpCode.setOnEditorActionListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_verification:
                registerPresenter.onSendVerificationClick(etPhoneNumber);
                break;
            case R.id.btn_verify:
                registerPresenter.onVerifyClick(etOtpCode);
                break;
            default:
                break;
        }
    }

    @Override
    public void onSendVerificationClick() {
        llEnterNumber.setVisibility(View.GONE);
        llEnterOtp.setVisibility(View.VISIBLE);
    }

    @Override
    public void onVerifyClick() {
        finish();
        registerPresenter.saveUserData(etPhoneNumber.getText().toString());
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Utility.KEY_SHOW_EDIT_PROFILE, true);
        startActivity(intent);
    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
            switch (v.getId()) {
                case R.id.et_phone_number:
                    onSendVerificationClick();
                    break;
                case R.id.et_otp_code:
                    onVerifyClick();
                    break;
            }
        }
        return false;
    }
}
