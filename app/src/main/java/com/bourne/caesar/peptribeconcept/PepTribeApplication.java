package com.bourne.caesar.peptribeconcept;

import android.app.Application;
import android.content.Context;

public class PepTribeApplication extends Application {
    static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
