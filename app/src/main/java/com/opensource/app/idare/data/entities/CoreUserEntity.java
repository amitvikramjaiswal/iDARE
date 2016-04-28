package com.opensource.app.idare.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

/**
 * Created by ajaiswal on 4/26/2016.
 */
public class CoreUserEntity extends GenericJson implements Parcelable {

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
    @Key("_name")
    private String name;
    @Key("_mobile")
    private String mobile;
    @Key("_email")
    private String email;
    @Key("_alternate_number")
    private String alternate;

    public CoreUserEntity() {

    }

    protected CoreUserEntity(Parcel in) {
        name = in.readString();
        mobile = in.readString();
        email = in.readString();
        alternate = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlternate() {
        return alternate;
    }

    public void setAlternate(String alternate) {
        this.alternate = alternate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(mobile);
        dest.writeString(email);
        dest.writeString(alternate);
    }
}
