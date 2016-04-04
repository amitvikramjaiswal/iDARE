package com.opensource.app.idare.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.opensource.app.idare.R;
import com.opensource.app.idare.view.activities.MainActivity;

/**
 * Created by ajaiswal on 4/4/2016.
 */
public class SettingsFragment extends BaseFragment {

    private MainActivity mMainActivity;
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        mMainActivity = (MainActivity) getActivity();
        mContext = mMainActivity.getApplicationContext();
        setHasOptionsMenu(true);
        mMainActivity.getSupportActionBar().setTitle(mMainActivity.getStringArray(R.array.arr_nav_titles)[5]);

        return view;
    }

    @Override
    int getFragmentLayoutResourceId() {
        return R.layout.fragment_settings;
    }

    @Override
    void findViews() {

    }
}
