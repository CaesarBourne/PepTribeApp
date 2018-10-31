package com.bourne.caesar.peptribeconcept.Retrofit;

import com.bourne.caesar.peptribeconcept.Retrofit.Interceptors.AddCookiesInterceptor;
import com.bourne.caesar.peptribeconcept.Retrofit.Interceptors.ReceivedCookiesInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient myInstance;
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://www.peptribe.co/";


    private static HttpLoggingInterceptor loggingInterceptor = new
            HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.BODY);
    private static OkHttpClient.Builder httpClient = new OkHttpClient.
            Builder().addInterceptor(loggingInterceptor)
            .connectTimeout(60 * 5, TimeUnit.SECONDS)
            .readTimeout(60 * 5, TimeUnit.SECONDS)
            .writeTimeout(60 * 5, TimeUnit.SECONDS)
            .addInterceptor(new ReceivedCookiesInterceptor())
            .addInterceptor(new AddCookiesInterceptor());

    private RetrofitClient (){
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }
//synchronised means only one thread can call this method to get the private constructor of this class
    public static synchronized RetrofitClient getInstance(){
        //if the instance of this clas is null
        if (myInstance == null){

            myInstance = new RetrofitClient();
        }
        return myInstance;
    }
    public Api getApi(){
        return retrofit.create(Api.class);
    }
}

//            OkHttpClient.Builder okHttpClient = new OkHttpClient().newBuilder()
//                    .connectTimeout(60 * 5, TimeUnit.SECONDS)
//                    .readTimeout(60 * 5, TimeUnit.SECONDS)
//                    .writeTimeout(60 * 5, TimeUnit.SECONDS);
//            okHttpClient.interceptors().add(new AddCookiesInterceptor());
//            okHttpClient.interceptors().add(new ReceivedCookiesInterceptor());

//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .client(okHttpClient.build())
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();