package com.opensource.app.idare.util;

import android.content.Context;

import com.kinvey.android.Client;

/**
 * Created by ajaiswal on 4/27/2016.
 */
public class KinveyUtil {

    public static Client getKinveyClient(Context context) {
        return new Client.Builder(context).build();
    }

}
