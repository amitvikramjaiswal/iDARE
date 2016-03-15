package com.opensource.app.idare.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.opensource.app.idare.R;
import com.opensource.app.idare.presenter.impl.MainPresenterImpl;
import com.opensource.app.idare.presenter.presenters.MainPresenter;
import com.opensource.app.idare.view.views.MainView;

public class MainActivity extends BaseActivity implements MainView {

    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenterImpl(this);
    }
}
