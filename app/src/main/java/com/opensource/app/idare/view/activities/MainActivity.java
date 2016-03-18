package com.opensource.app.idare.view.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.Button;

import com.opensource.app.idare.R;
import com.opensource.app.idare.presenter.impl.MainPresenterImpl;
import com.opensource.app.idare.presenter.presenters.MainPresenter;
import com.opensource.app.idare.view.fragments.MyAccountPassiveFragment;
import com.opensource.app.idare.view.fragments.NavigationDrawerFragment;
import com.opensource.app.idare.view.views.MainView;

public class MainActivity extends BaseActivity implements MainView, NavigationDrawerFragment.NavigationDrawerCallbacks, View.OnClickListener {

    MainPresenter mainPresenter;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;
    private String[] mTitles;

    private Button btnMakePassive;

    @Override
    protected void onBaseActivityCreate(Bundle savedInstanceState) {
        super.onBaseActivityCreate(savedInstanceState);
        mTitles = getStringArray(R.array.arr_nav_titles);
        setContentView(R.layout.activity_main);
        findViews();
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        mainPresenter = new MainPresenterImpl(this);
    }

    private void findViews() {
        btnMakePassive = (Button) findViewById(R.id.btn_make_passive);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        mTitle = mTitles[position];
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {

            case 0:
                fragment = new MyAccountPassiveFragment();
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                hideProgressDialog();
                break;
            default:
                break;
        }
        if (fragment != null) {
            replaceFragment(fragment);
        }

    }

    //Method to replace the Fragment
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    public void popFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_make_passive:
                popFragment();
                break;
        }
    }
}
