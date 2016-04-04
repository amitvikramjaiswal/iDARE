package com.opensource.app.idare.view.views;

import android.content.SharedPreferences;
import android.view.View;

import com.opensource.app.idare.service.handlers.AlertDialogHandler;

/**
 * Created by ajaiswal on 3/15/2016.
 */
public interface BaseView {

    /**
     * Shows a toast message.
     *
     * @param message The message to show.
     */
    void showToast(String message);

    /**
     * Shows or hides the indefinite progress bar.
     *
     * @param shown Whether the progress bar should be shown.
     */
    void setProgressBarIndeterminateVisibility(boolean shown);

    /**
     * Sets the app title.
     *
     * @param title The title.
     */
    void setTitle(int title);

    /**
     * Sets the app title.
     *
     * @param title The title.
     */
    void setTitle(CharSequence title);

    /**
     * Gets a string from app resources.
     *
     * @param stringResourceId The string resource id.
     * @param formatArgs The format arguments that will be used for
     *                         substitution.
     * @return The string.
     */
    String getString(int stringResourceId, Object... formatArgs);

    /**
     * Gets a string array from app resources.
     *
     * @param arrayResourceId The array resource id.
     * @return The string array.
     */
    String[] getStringArray(int arrayResourceId);

    /**
     * Closes the view.
     */
    void finish();

    SharedPreferences getPreferences();

    /**
     * Checks if Google Play Services is available.
     */
    boolean checkGooglePlayServices();

    /**
     * Checks if the view is currently finishing.
     */
    boolean isFinishing();

    /**
     * Shows an alert dialog to prompt the user.
     *
     * @param title              The title of the dialog, optional.
     * @param message            The message for the dialog.
     * @param positiveButton     The positive button (optional).
     * @param negativeButton     The negative button (optional).
     * @param alertDialogHandler The dialog handler for button clicks.
     */
    void showAlertDialog(String title, String message, String positiveButton, String negativeButton, AlertDialogHandler alertDialogHandler);

    /**
     * Shows a progress dialog in the center of the screen.
     *
     * @param status The status message to show.
     */
    void showProgressDialog(String status);

    /**
     * Hides the progress dialog from the center of the screen.
     */
    void hideProgressDialog();

    void hideKeyboard();

    void shakeView(View view);

}
