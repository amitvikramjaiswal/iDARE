package com.opensource.app.idare.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.opensource.app.idare.R;
import com.opensource.app.idare.presenter.impl.MainPresenterImpl;
import com.opensource.app.idare.presenter.presenters.MainPresenter;
import com.opensource.app.idare.service.handlers.AlertDialogHandler;
import com.opensource.app.idare.util.Utility;
import com.opensource.app.idare.view.fragments.AppTourFragment;
import com.opensource.app.idare.view.fragments.CoreListFragment;
import com.opensource.app.idare.view.fragments.DonateFragment;
import com.opensource.app.idare.view.fragments.InviteToIDareFragment;
import com.opensource.app.idare.view.fragments.NavigationDrawerFragment;
import com.opensource.app.idare.view.fragments.PassiveProfileFragment;
import com.opensource.app.idare.view.fragments.SettingsFragment;
import com.opensource.app.idare.view.views.MainView;

public class MainActivity extends BaseActivity implements MainView, NavigationDrawerFragment.NavigationDrawerCallbacks, View.OnClickListener {

    private static final String TAG = "MainActivity";
    MainPresenter mainPresenter;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;
    private String[] mTitles;
    private AlertDialog alertDialog;
    private String backStateName;
    private boolean fragmentPopped;
    private FragmentManager.BackStackEntry backStackEntry;
    private FragmentManager fragmentManager;

    private Button btnMakePassive;

    @Override
    protected void onBaseActivityCreate(Bundle savedInstanceState) {
        super.onBaseActivityCreate(savedInstanceState);
        mTitles = getStringArray(R.array.arr_nav_titles);
        fragmentManager = getSupportFragmentManager();
        setContentView(R.layout.activity_main);
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        mainPresenter = new MainPresenterImpl(this);
    }

    public void addListeners() {
        btnMakePassive.setOnClickListener(this);
    }

    public void findViews() {
        btnMakePassive = (Button) findViewById(R.id.btn_make_passive);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        mTitle = mTitles[position];
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new PassiveProfileFragment();
                break;
            case 1:
                fragment = new CoreListFragment();
                break;
            case 2:
                fragment = new InviteToIDareFragment();
                break;
            case 3:
                fragment = new AppTourFragment();
                break;
            case 4:
                fragment = new DonateFragment();
                break;
            case 5:
                fragment = new SettingsFragment();
                break;
            case 6:
                logout();
                break;
            default:
                break;
        }
        if (fragment != null) {
            replaceFragment(fragment);
        }
    }

    private void logout() {
        showAlertDialog(getString(R.string.app_name), getString(R.string.logout_message), getString(R.string.btn_logout), getString(R.string.btn_cancel), new AlertDialogHandler() {
            @Override
            public void onPositiveButtonClicked() {
                mainPresenter.logout();
            }

            @Override
            public void onNegativeButtonClicked() {

            }
        });
    }

    //Method to replace the Fragment
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_make_passive:
                showMakePassivePopUp();
                break;
            case R.id.btn_make_passive_confirm:
                if (alertDialog != null)
                    alertDialog.dismiss();
                mainPresenter.replaceFragment(new PassiveProfileFragment());
                break;
        }
    }

    @Override
    public void showMakePassivePopUp() {
        mNavigationDrawerFragment.hideDrawer();

        View popupView = getLayoutInflater().inflate(R.layout.layout_popup_view, null);
        Button button = (Button) popupView.findViewById(R.id.btn_make_passive_confirm);
        button.setOnClickListener(this);

        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setView(popupView);
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        alertDialog.show();
    }

    @Override
    public void relaunch() {
        finish();
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
