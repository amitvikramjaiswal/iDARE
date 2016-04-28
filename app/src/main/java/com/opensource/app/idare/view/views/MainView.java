package com.opensource.app.idare.view.views;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by ajaiswal on 3/15/2016.
 */
public interface MainView extends BaseView {
    void addListeners();

    void findViews();

    void replaceFragment(Fragment fragment);

    void showMakePassivePopUp();

    void relaunch();

    void toggleMakePassiveButton();

    Context getContext();
}
