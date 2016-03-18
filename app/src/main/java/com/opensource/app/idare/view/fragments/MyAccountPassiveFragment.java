package com.opensource.app.idare.view.fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.opensource.app.idare.R;
import com.opensource.app.idare.view.activities.MainActivity;

/**
 * Created by ajaiswal on 3/18/2016.
 */
public class MyAccountPassiveFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "MyAccountPassiveFragment";
    private MainActivity mMainActivity;
    private Context mContext;
    private TextView tvWelcomeTitle;
    private Button btnMakeActive;

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
        String welcomeTitle = getString(R.string.welcome_title, "Amit Vikram Jaiswal");
        SpannableString spannableString = new SpannableString(welcomeTitle);
        spannableString.setSpan(new StyleSpan(Typeface.BOLD), 8, welcomeTitle.length(), 0);
        tvWelcomeTitle.setText(spannableString);
    }

    private void addListeners() {
        btnMakeActive.setOnClickListener(this);
    }

    @Override
    int getFragmentLayoutResourceId() {
        return R.layout.fragment_my_account_passive;
    }

    @Override
    void findViews() {
        tvWelcomeTitle = (TextView) mRootView.findViewById(R.id.tv_welcome_title);
        btnMakeActive = (Button) mRootView.findViewById(R.id.btn_make_active);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_make_active:
                mMainActivity.replaceFragment(new MyAccountActiveFragment());
                break;
            default:
                break;
        }
    }
}
