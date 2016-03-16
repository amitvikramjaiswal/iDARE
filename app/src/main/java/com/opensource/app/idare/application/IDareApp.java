package com.opensource.app.idare.application;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

/**
 * Created by ajaiswal on 3/16/2016.
 */
public class IDareApp extends MultiDexApplication {

    private static final String TAG = "IDareApp";
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        setContext(getApplicationContext());

    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        IDareApp.context = context;
    }
}
