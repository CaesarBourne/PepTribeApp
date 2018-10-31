package com.bourne.caesar.peptribeconcept.Storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.bourne.caesar.peptribeconcept.Constants.Constants;

import java.util.HashSet;

public class SharedPreferencesManager {

    public static final String SHARED_PREF_FILE ="sharedfile";
    private Context mycontext;
    private static SharedPreferencesManager newsharedInstance;

    private SharedPreferencesManager(Context mycontext) {
        this.mycontext = mycontext;
    }


    public static synchronized SharedPreferencesManager getSharedPrefInstance( Context mycontext){
        if (newsharedInstance == null){
            newsharedInstance = new SharedPreferencesManager(mycontext);
        }
        return newsharedInstance;
    }

    public boolean setCookies( HashSet<String> cookies) {
        SharedPreferences mysharedpreferences = mycontext.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor sharededitor = mysharedpreferences.edit();
        return sharededitor.putStringSet("cookies", cookies).commit();
    }

    public static   HashSet<String> getCookies(Context context) {
        SharedPreferences hashSharedPreferences = context.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
        return (HashSet<String>) hashSharedPreferences.getStringSet("cookies", new HashSet<String>());
    }

    public void saveToken(String tokenString){
        SharedPreferences mysharedpreferences = mycontext.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor sharededitor = mysharedpreferences.edit();
        sharededitor.putString(Constants.TOKEN_SAVED_USER, tokenString);
        sharededitor.apply();
    }
    public void saveUserId(int userid){
        SharedPreferences mysharedpreferences = mycontext.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor sharededitor = mysharedpreferences.edit();
        sharededitor.putInt(Constants.USER_ID, userid);
        sharededitor.apply();
    }

    public boolean isLoggedIn(){
        SharedPreferences logsharedpreference = mycontext.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
// After setting the default value of the shared preference to -1 check if its been changed i.e user has been logged in before
        boolean logedStatus = logsharedpreference.getInt(Constants.USER_ID, -1) != -1;
        return logedStatus;
    }
    public String getToken(String tokenKey){
        SharedPreferences tokenSharedPreferences = mycontext.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
        String userToken = tokenSharedPreferences.getString(tokenKey, null);
        return userToken;
    }
    public int getUserId(String useridKey){
        SharedPreferences uidsharedPreferences = mycontext.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
        int userId = uidsharedPreferences.getInt(useridKey, -1);
        return userId;
    }
    public void clearLogout(){
        SharedPreferences logoutsharedpreferences = mycontext.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor logoutsharededitor = logoutsharedpreferences.edit();
        logoutsharededitor.clear();
        logoutsharededitor.apply();
    }
}
