package com.bourne.caesar.peptribeconcept.Retrofit;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {


    private int user_id;
    private String access_token;

    public LoginResponse(int user_id, String access_token) {
        this.user_id = user_id;
        this.access_token = access_token;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getAccess_token() {
        return access_token;
    }
}
