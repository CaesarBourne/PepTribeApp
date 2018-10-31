package com.bourne.caesar.peptribeconcept.Retrofit.Interceptors;

import android.app.Application;
import android.content.Context;

import com.bourne.caesar.peptribeconcept.MainActivity;
import com.bourne.caesar.peptribeconcept.PepTribeApplication;
import com.bourne.caesar.peptribeconcept.Storage.SharedPreferencesManager;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ReceivedCookiesInterceptor implements Interceptor {
    Context context;
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
////        check if the request requires to have a cookie
//        context = PepTribeApplication.getContext();
//        if (originalResponse.header("No-Authentication") == null) {
//
//            if (!originalResponse.headers("Set-Cookie").isEmpty()) {
//                HashSet<String> cookies = new HashSet<>();
//                for (String header : originalResponse.headers("Set-Cookie")) {
//                    cookies.add(header);
//                }
//                SharedPreferencesManager.getSharedPrefInstance(context).setCookies(cookies);
////                SharedPreferencesManager.setCookies(context, cookies);
//            }
//        }
        return originalResponse;
    }

}
