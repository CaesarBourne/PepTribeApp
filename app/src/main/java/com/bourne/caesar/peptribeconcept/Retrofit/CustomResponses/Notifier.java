package com.bourne.caesar.peptribeconcept.Retrofit.CustomResponses;

public class Notifier {

    private String username;
    private String avatar;

    public Notifier(String username, String avatar) {
        this.username = username;
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatar() {
        return avatar;
    }
}
