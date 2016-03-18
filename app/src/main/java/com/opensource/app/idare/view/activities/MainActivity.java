package com.opensource.app.idare.view.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;

import com.opensource.app.idare.R;
import com.opensource.app.idare.presenter.impl.MainPresenterImpl;
import com.opensource.app.idare.presenter.presenters.MainPresenter;
import com.opensource.app.idare.view.fragments.NavigationDrawerFragment;
import com.opensource.app.idare.view.views.MainView;

public class MainActivity extends BaseActivity implements MainView, NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;

    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        mainPresenter = new MainPresenterImpl(this);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {

            case 0:
//                mTitle = getString(R.string.navigation_item1);
//                fragment = new DeviceUsersFragment();
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
        if (position != 6) {
//            replaceFragment(fragment);
        }
    }
}
