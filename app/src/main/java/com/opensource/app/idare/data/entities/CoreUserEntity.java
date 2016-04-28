package com.opensource.app.idare.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.api.client.json.GenericJson;

/**
 * Created by ajaiswal on 4/26/2016.
 */
public class CoreUserEntity extends GenericJson implements Parcelable {
    protected CoreUserEntity(Parcel in) {
    }

    public static final Creator<CoreUserEntity> CREATOR = new Creator<CoreUserEntity>() {
        @Override
        public CoreUserEntity createFromParcel(Parcel in) {
            return new CoreUserEntity(in);
        }

        @Override
        public CoreUserEntity[] newArray(int size) {
            return new CoreUserEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
