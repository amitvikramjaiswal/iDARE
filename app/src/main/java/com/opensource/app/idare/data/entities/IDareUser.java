package com.opensource.app.idare.data.entities;

import com.google.api.client.util.Key;
import com.kinvey.java.LinkedResources.LinkedGenericJson;
import com.opensource.app.idare.util.Utility;

/**
 * Created by ajaiswal on 5/3/2016.
 */
public class IDareUser extends LinkedGenericJson {

    @Key(Utility.KINVEY_NAME)
    private String name;
    @Key(Utility.KINVEY_EMAIL)
    private String email;
    @Key(Utility.KINVEY_MOBILE)
    private String mobile;
    @Key(Utility.KINVEY_ALTERNATE)
    private String alternate;

    public IDareUser() {
        putFile(Utility.KINVEY_PROFILE_PIC);
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

    public String getAlternate() {
        return alternate;
    }

    public void setAlternate(String alternate) {
        this.alternate = alternate;
    }
}
