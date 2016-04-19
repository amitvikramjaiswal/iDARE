package com.opensource.app.idare.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.opensource.app.idare.R;
import com.opensource.app.idare.application.IDareApp;
import com.opensource.app.idare.service.handlers.AlertDialogHandler;
import com.opensource.app.idare.util.Utils;
import com.opensource.app.idare.view.activities.EditProfileActivity;
import com.opensource.app.idare.view.activities.MainActivity;

/**
 * Created by ajaiswal on 3/18/2016.
 */
public class PassiveProfileFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "PassiveProfileFragment";
    private MainActivity mMainActivity;
    private Context mContext;
    private TextView tvWelcomeTitle;
    private Button btnMakeActive;
    private ImageView ivEditProfile;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        mMainActivity = (MainActivity) getActivity();
        mContext = mMainActivity.getApplicationContext();
        setHasOptionsMenu(true);
        mMainActivity.getSupportActionBar().setTitle(mMainActivity.getStringArray(R.array.arr_nav_titles)[0]);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setValues();
        addListeners();
    }

    private void setValues() {
        String name = IDareApp.getUserContext() == null ? "" : IDareApp.getUserContext().getName();
        String welcomeTitle = getString(R.string.welcome_title, name);
        SpannableString spannableString = new SpannableString(welcomeTitle);
        spannableString.setSpan(new StyleSpan(Typeface.BOLD), 8, welcomeTitle.length(), 0);
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                onEditProfileClick();
            }
        }, 8, welcomeTitle.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvWelcomeTitle.setHighlightColor(Color.BLACK);
        tvWelcomeTitle.setMovementMethod(LinkMovementMethod.getInstance());
        tvWelcomeTitle.setText(spannableString);
    }

    private void addListeners() {
        btnMakeActive.setOnClickListener(this);
        ivEditProfile.setOnClickListener(this);
    }

    @Override
    int getFragmentLayoutResourceId() {
        return R.layout.fragment_my_account_passive;
    }

    @Override
    void findViews() {
        tvWelcomeTitle = (TextView) mRootView.findViewById(R.id.tv_welcome_title);
        btnMakeActive = (Button) mRootView.findViewById(R.id.btn_make_active);
        ivEditProfile = (ImageView) mRootView.findViewById(R.id.iv_edit_profile);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_make_active:
                onMakeActiveClicked();
                break;
            case R.id.iv_edit_profile:
                onEditProfileClick();
                break;
            default:
                break;
        }
    }

    public void onEditProfileClick() {
        Intent intent = new Intent(mMainActivity, EditProfileActivity.class);
        startActivity(intent);
    }

    private void onMakeActiveClicked() {
        if (Utils.isLocationServicesEnabled(mContext)) {
            mMainActivity.replaceFragment(new ActiveProfileFragment());
        } else {
            mMainActivity.showAlertDialog(null, "The active mode wants to access your location.", getString(R.string.btn_ok), getString(R.string.btn_cancel), new AlertDialogHandler() {
                @Override
                public void onPositiveButtonClicked() {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }

                @Override
                public void onNegativeButtonClicked() {

                }
            });
        }
    }
}
