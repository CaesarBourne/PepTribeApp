package com.bourne.caesar.peptribeconcept.Retrofit.CustomResponses;

public class Notifications {

    private Notifier notifier;

    private String type_text;
    private String icon;

    private String time_text_string;

    public Notifications(Notifier notifier, String type_text, String time_text_string, String icon) {
        this.notifier = notifier;
        this.type_text = type_text;
        this.icon = icon;
        this.time_text_string = time_text_string;
    }

    public Notifier getNotifier() {
        return notifier;
    }

    public String getType_text() {
        return type_text;
    }

    public String getTime_text_string() {
        return time_text_string;
    }

    public String getIcon() {
        return icon;
    }
}
