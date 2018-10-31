package com.bourne.caesar.peptribeconcept.Retrofit.CustomResponses;

import com.google.gson.annotations.SerializedName;

import okhttp3.Cookie;

public class CookieTestResponse {

    @SerializedName("Set-Cookie")
    public String cookie;

    @SerializedName("Server")
    public String server;

    @SerializedName("Expires")
    public String expires;

    public CookieTestResponse(String cookie, String server, String expires) {
        this.cookie = cookie;
        this.server = server;
        this.expires = expires;
    }

    public String getCookie() {
        return cookie;
    }

    public String getServer() {
        return server;
    }

    public String getExpires() {
        return expires;
    }
}
