package com.bourne.caesar.peptribeconcept.Network;

import android.content.Context;
import android.net.ConnectivityManager;

public class Connection {

    public static boolean connectionAvaialable(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }
}
