package com.opensource.app.idare.data.entities;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

/**
 * Created by ajaiswal on 4/26/2016.
 */
public class CoreUserEntity extends GenericJson {

    @Key("name")
    private String name;
    @Key("mobile")
    private String mobile;
    @Key("email")
    private String email;
    @Key("alternate_number")
    private String alternate;

    public CoreUserEntity() {

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
