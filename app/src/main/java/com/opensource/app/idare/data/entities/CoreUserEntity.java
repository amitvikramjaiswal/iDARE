package com.opensource.app.idare.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

/**
 * Created by ajaiswal on 4/26/2016.
 */
public class CoreUserEntity extends GenericJson {

    @Key("_id")
    private String id;
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

}
