package com.opensource.app.idare.view.activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Spanned;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.opensource.app.idare.R;
import com.opensource.app.idare.service.handlers.AlertDialogHandler;
import com.opensource.app.idare.view.views.BaseView;

/**
 * Created by ajaiswal on 3/15/2016.
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private static final String TAG = "BaseActivity";
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

       /* Change the statusBar color if the version is above Lollipop,Because this feature implemented in API 21*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        onBaseActivityCreate(savedInstanceState);
        setProgressBarIndeterminateVisibility(false);

    }

    /**
     * Use this function instead of onCreate to implement functionality.
     *
     * @param savedInstanceState The saved instance state.
     */
    protected void onBaseActivityCreate(Bundle savedInstanceState) {

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public String[] getStringArray(int arrayResourceId) {
        return getResources().getStringArray(arrayResourceId);
    }

    @Override
    public boolean checkGooglePlayServices() {
        int errorCode = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getApplicationContext());
        if (errorCode == ConnectionResult.SUCCESS) {
            return true;
        } else {
            Dialog d = GoogleApiAvailability.getInstance().getErrorDialog(this, errorCode, 0);
            if (d != null) {
                d.show();
            } else {
                showToast("Could not initialize Google Play Services.");
            }
            return false;
        }
    }

    @Override
    public void showProgressDialog(String status) {
        hideProgressDialog();

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(status);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showAlertDialog(String title, String message, String positiveButton, String negativeButton, final AlertDialogHandler alertDialogHandler) {
        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);

        if (title != null) {
            myAlertDialog.setTitle(title);
        }

        if (message != null) {
            myAlertDialog.setMessage(message);
        }

        if (positiveButton != null) {
            myAlertDialog.setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface arg0, int arg1) {
                    if (alertDialogHandler != null) {
                        alertDialogHandler.onPositiveButtonClicked();
                    }
                }
            });
        }

        if (negativeButton != null) {
            myAlertDialog.setNegativeButton(negativeButton, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface arg0, int arg1) {
                    if (alertDialogHandler != null) {
                        alertDialogHandler.onNegativeButtonClicked();
                    }
                }
            });
            myAlertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

                @Override
                public void onCancel(DialogInterface dialog) {
                    alertDialogHandler.onNegativeButtonClicked();
                }
            });
        } else {
            myAlertDialog.setCancelable(false);
        }

        myAlertDialog.show();
    }

    @Override
    public void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) this
                .getSystemService(INPUT_METHOD_SERVICE);
        View currentFocus = this.getCurrentFocus();
        if (currentFocus != null) {
            IBinder windowToken = this.getCurrentFocus().getWindowToken();
            if (windowToken != null) {
                inputMethodManager.hideSoftInputFromWindow(windowToken, 0);
            }
        }
    }

    public void shakeView(View view) {
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        view.startAnimation(shake);
    }
}
