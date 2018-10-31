package com.bourne.caesar.peptribeconcept.Retrofit;

import com.bourne.caesar.peptribeconcept.Constants.Constants;
import com.bourne.caesar.peptribeconcept.Retrofit.CustomResponses.CookieTestResponse;
import com.bourne.caesar.peptribeconcept.Retrofit.CustomResponses.GeneralDataResponse;
import com.bourne.caesar.peptribeconcept.Retrofit.CustomResponses.UserDataResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface Api {
//THE VARIABLES ARE CREATED HERE
    @FormUrlEncoded
    @POST("api/create-account")
    @Headers("No-Authentication: true")
    Call<LoginResponse> createUser(
            @Field(Constants.USERNAME) String username,
            @Field(Constants.EMAIL) String email,
            @Field(Constants.PASSWORD) String password,
            @Field(Constants.CONFIRM_PASSWORD) String confirm_password,
            @Field(Constants.SERVER_KEY) String server_key

    );

//    @FormUrlEncoded
//    @POST("get-user-data")
//    Call<UserData> getUserData(
//            @Field(Constants.)
//    );

    @FormUrlEncoded
    @POST("/api/get-general-data")
    Call<GeneralDataResponse> getNotifications(
            @Query(Constants.TOKEN_API) String token ,
            @Field(Constants.SERVER_KEY) String server_key,
            @Field(Constants.FETCH) String getNotifications
    );

    @FormUrlEncoded
    @POST("api/auth")
    @Headers("No-Authentication: true")
    Call<LoginResponse> loginUser(
            @Field(Constants.USERNAME) String username,
            @Field(Constants.PASSWORD) String password,
            @Field(Constants.SERVER_KEY) String server_key
    );

    @FormUrlEncoded
    @POST("api/get-user-data")
    @Headers("No-Authentication: true")
    Call<UserDataResponse> getUserData(
            @Query(Constants.TOKEN_API) String token,
            @Field(Constants.USER_ID) int userid,
            @Field(Constants.FETCH)String userdata,
            @Field(Constants.SERVER_KEY) String server_key
            );


    @GET("get_news_feed")
    Call<ResponseBody> getNewsfeed(
            @Header(Constants.SERVER_KEY) String server_key
//            @Query(Constants.TOKEN_API) String token
    );

    @FormUrlEncoded
    @POST("api/set-browser-cookie")
    Call<CookieTestResponse> getBrowserCookie(
      @Field(Constants.SERVER_KEY) String server_key,
      @Query(Constants.TOKEN_API) String token
    );

    @FormUrlEncoded
    @POST("api/update-user-data")
    Call<ResponseBody> updateUserData(
            @Field(Constants.USERNAME) String useraname
    );

}
