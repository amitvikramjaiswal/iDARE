package com.opensource.app.idare.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ajaiswal on 4/19/2016.
 */
public class UserContext implements Parcelable {

    private String id;
    private String name;
    private String email;
    private String mobile;
    private String alternateMobile;
    private String filePath;

    public UserContext() {

    }

    protected UserContext(Parcel in) {
        id = in.readString();
        name = in.readString();
        email = in.readString();
        mobile = in.readString();
        alternateMobile = in.readString();
        filePath = in.readString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAlternateMobile() {
        return alternateMobile;
    }

    public void setAlternateMobile(String alternateMobile) {
        this.alternateMobile = alternateMobile;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(mobile);
        dest.writeString(alternateMobile);
        dest.writeString(filePath);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserContext> CREATOR = new Creator<UserContext>() {
        @Override
        public UserContext createFromParcel(Parcel in) {
            return new UserContext(in);
        }

        @Override
        public UserContext[] newArray(int size) {
            return new UserContext[size];
        }
    };
}
