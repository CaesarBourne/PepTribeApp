package com.bourne.caesar.peptribeconcept.Retrofit.Interceptors;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.bourne.caesar.peptribeconcept.Constants.Constants;
import com.bourne.caesar.peptribeconcept.PepTribeApplication;
import com.bourne.caesar.peptribeconcept.Storage.SharedPreferencesManager;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class AddCookiesInterceptor implements Interceptor {
    Context context;
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
//        context = PepTribeApplication.getContext();
//        //check if the request needs a cookie first with the annotated headers in the method for making the request
//        if (request.header("No-Authentication") == null) {
//
////            HashSet<String> preferences = SharedPreferencesManager.getSharedPrefInstance(context).getCookies();
//            HashSet<String> preferences = SharedPreferencesManager.getCookies(context);
//            for (String cookie : preferences) {
//                request  = request.newBuilder().addHeader("Cookie", cookie).build();
//                Log.v("OkHttp", "Tahis header is added: " + cookie);
//            }
//        }
        return chain.proceed(request);
    }
}
