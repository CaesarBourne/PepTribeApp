package com.bourne.caesar.peptribeconcept;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bourne.caesar.peptribeconcept.Constants.Constants;
import com.bourne.caesar.peptribeconcept.Registration.RegisterActivity;
import com.bourne.caesar.peptribeconcept.Retrofit.LoginResponse;
import com.bourne.caesar.peptribeconcept.Retrofit.RetrofitClient;
import com.bourne.caesar.peptribeconcept.SSLHttpsManager.HttpsTrustManager;
import com.bourne.caesar.peptribeconcept.Storage.SharedPreferencesManager;

import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    TextView registergoto, usernameView;
    EditText  passwordView;
    ProgressBar progressBar;
    public static String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HttpsTrustManager.allowAllSSL();
        passwordView = findViewById(R.id.password);

        progressBar = findViewById(R.id.progressBar);
        usernameView = findViewById(R.id.input_name);
        registergoto = findViewById(R.id.link_signup);

        passwordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        registergoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (SharedPreferencesManager.getSharedPrefInstance(MainActivity.this).isLoggedIn()){
            Toast.makeText(MainActivity.this, "Userid saved ",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, MainFeed.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }

    private void attemptLogin() {



        // Store values at the time of the login attempt.
        String password = passwordView.getText().toString().trim();
        String username = usernameView.getText().toString().trim();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one., if password is not empty but not validi.e someting is in password but not long enough
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            passwordView.setError(getString(R.string.error_invalid_password));
            focusView = passwordView;
            cancel = true;
        }

        // Check for a valid username.
        if (TextUtils.isEmpty(username)) {
            usernameView.setError(getString(R.string.error_field_required));
            focusView = usernameView;
            cancel = true;
        } else if (!isUsernameValid(username)) {
            usernameView.setError(getString(R.string.error_invalid_username));
            focusView = usernameView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            progressBar.setVisibility(View.VISIBLE);
            final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Authenticating...");
            progressDialog.show();
            // Reset errors.
            usernameView.setError(null);
            passwordView.setError(null);
//             Show a progress spinner, and kick off a background task to
//             perform the user login attempt.
//            showProgress(true);
            Call<LoginResponse> call = RetrofitClient.getInstance().getApi().loginUser(username,
                    password, Constants.VALUE_SERVER_KEY);

            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                    if (response.isSuccessful() && response.body().getAccess_token() == null){
                        LoginResponse loginResponse = response.body();
                        String userToken = loginResponse.getAccess_token();
                        int userID = loginResponse.getUser_id();
                        SharedPreferencesManager.getSharedPrefInstance(MainActivity.this).saveUserId(userID);
                        Log.v(TAG,"This is the User Id " + userID);
                        Log.v(TAG,"This is the Token " + userToken);
                        SharedPreferencesManager.getSharedPrefInstance(MainActivity.this).saveToken(userToken);
                        Toast.makeText(MainActivity.this,"You have been looged in boss", Toast.LENGTH_LONG).show();
                        String usertoken = SharedPreferencesManager.getSharedPrefInstance(MainActivity.this).getToken(Constants.TOKEN_SAVED_USER);
                        int userId =  SharedPreferencesManager.getSharedPrefInstance(MainActivity.this).getUserId(Constants.USER_ID);
                        Toast.makeText(MainActivity.this,"You have been looged in boss "+ userToken, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, MainFeed.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }else {
                        Toast.makeText(MainActivity.this,"Your username or password is incorrect, you are not a registered uder", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(MainActivity.this,"Your username or password is incorrect or bad connection", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            });

//            Call<ResponseBody> callE = RetrofitClient.getInstance().getApi().loginUser(
//                    username, password, Constants.VALUE_SERVER_KEY);
//            callE.enqueue(new Callback<ResponseBody>() {
//                @Override
//                public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
//                   try {
//                       ResponseBody responseBody = response.body();
//                       String userToken = responseBody.string();
//                   } catch (IOException e) {
//                       e.printStackTrace();
//                   }
//
////                        int userID = loginResponse.getUser_id();
////                        SharedPreferencesManager.getSharedPrefInstance(MainActivity.this).saveUserId(userID);
////                        Log.v(TAG,"This is the User Id " + userID);
////                        Log.v(TAG,"This is the Token " + userToken);
////                        SharedPreferencesManager.getSharedPrefInstance(MainActivity.this).saveToken(userToken);
////                        Toast.makeText(MainActivity.this,"You have been looged in boss", Toast.LENGTH_LONG).show();
//                }
//
//                @Override
//                public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//                }
//            });

        }
    }
    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    private boolean isUsernameValid(String username) {
        //TODO: Replace this with your own logic
        return username.length() > 4 && username.length()< 10;
    }

    public void startOther(View view){
//        Intent intent = new Intent(MainActivity.this, MainFeed.class);
//        startActivity(intent);
        attemptLogin();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public void startProfile(View view){
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);
    }
    public void onboardingstart (View view){
        Intent intent = new Intent(MainActivity.this, OnboardingActivity.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }



}
