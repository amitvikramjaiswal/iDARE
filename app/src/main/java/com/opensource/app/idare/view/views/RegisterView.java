package com.opensource.app.idare.view.views;

/**
 * Created by ajaiswal on 3/21/2016.
 */
public interface RegisterView extends BaseView {
    void findViews();

    void setValues();

    void setListeners();

    void onSendVerificationClick();

    void onVerifyClick();
}
