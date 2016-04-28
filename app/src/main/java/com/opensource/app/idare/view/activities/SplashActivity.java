package com.opensource.app.idare.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.gson.Gson;
import com.opensource.app.idare.R;
import com.opensource.app.idare.application.IDareApp;
import com.opensource.app.idare.data.entities.UserContext;
import com.opensource.app.idare.presenter.presenters.BasePresenter;
import com.opensource.app.idare.util.Utility;

/**
 * Created by ajaiswal on 4/4/2016.
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);
        finishOnUiThread(getPreferences().getBoolean(Utility.KEY_NOT_FIRST_LAUNCH, false));
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    public void finishOnUiThread(final boolean isNotFirstLaunch) {
        new Handler().postDelayed(new Runnable() {
            /*
             * Showing splash screen with a timer. This will be useful when you
             */
            @Override
            public void run() {
                finish();
                Intent i;
                if (isNotFirstLaunch) {
                    UserContext userContext = new Gson().fromJson(getPreferences().getString(Utility.KEY_USER_CONTEXT, null), UserContext.class);
                    userContext = userContext == null ? new UserContext() : userContext;
                    IDareApp.setUserContext(userContext);
                    i = new Intent(SplashActivity.this, MainActivity.class);
                } else {
                    i = new Intent(SplashActivity.this, RegisterActivity.class);
                }
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        }, Utility.SPLASH_TIME_OUT);
    }
}
