package com.bourne.caesar.peptribeconcept.Registration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bourne.caesar.peptribeconcept.Constants.Constants;
import com.bourne.caesar.peptribeconcept.MainActivity;
import com.bourne.caesar.peptribeconcept.MainFeed;
import com.bourne.caesar.peptribeconcept.OnboardingActivity;
import com.bourne.caesar.peptribeconcept.ProfileActivity;
import com.bourne.caesar.peptribeconcept.R;
import com.bourne.caesar.peptribeconcept.Retrofit.LoginResponse;
import com.bourne.caesar.peptribeconcept.Retrofit.RetrofitClient;
import com.bourne.caesar.peptribeconcept.SSLHttpsManager.HttpsTrustManager;
import com.bourne.caesar.peptribeconcept.Storage.SharedPreferencesManager;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    TextView  emailView, logingoto, usernameView;
    EditText passwordView, reEnterPasswordView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        HttpsTrustManager.allowAllSSL();
        passwordView = findViewById(R.id.password);
        reEnterPasswordView = findViewById(R.id.reEnterPassword);
        emailView = findViewById(R.id.email);
        progressBar = findViewById(R.id.progressBar);
        logingoto = findViewById(R.id.link_login);
        usernameView = findViewById(R.id.input_name);

        reEnterPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptRegister();
                    return true;
                }
                return false;
            }
        });



        logingoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_out, R.anim.push_left_in);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (SharedPreferencesManager.getSharedPrefInstance(RegisterActivity.this).isLoggedIn()){
            Toast.makeText(RegisterActivity.this, "Userid saved boss",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(RegisterActivity.this, MainFeed.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }


    private void attemptRegister() {


        // Reset errors.
        emailView.setError(null);
        passwordView.setError(null);
        usernameView.setError(null);

        // Store values at the time of the login attempt.
        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();
        String reEnterpassword = reEnterPasswordView.getText().toString();
        String username = usernameView.getText().toString();


        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one., if password is not empty but not valid
        // i.e someting is in password but not long enough one righ
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password) ) {
            passwordView.setError(getString(R.string.error_invalid_password));
            focusView = passwordView;
            cancel = true;
        }
        //Check if the passwords match
        if (!TextUtils.equals(password, reEnterpassword)){
            reEnterPasswordView.setError(getString(R.string.renter_password_invalid));
            focusView = reEnterPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            emailView.setError(getString(R.string.error_field_required));
            focusView = emailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            emailView.setError(getString(R.string.error_invalid_email));
            focusView = emailView;
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
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
//            showProgress(true);

            progressBar.setVisibility(View.VISIBLE);
            final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this,
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Authenticating...");
            progressDialog.show();
            Call<LoginResponse> call = RetrofitClient.getInstance().getApi().createUser(username,
                    email, password,reEnterpassword, Constants.VALUE_SERVER_KEY);

            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful()){
                        LoginResponse loginResponse = response.body();
                        String userToken = loginResponse.getAccess_token();
                        int userID = loginResponse.getUser_id();
                        SharedPreferencesManager.getSharedPrefInstance(RegisterActivity.this).saveUserId(userID);
                        SharedPreferencesManager.getSharedPrefInstance(RegisterActivity.this).saveToken(userToken);
                        Toast.makeText(RegisterActivity.this,"You have been looged in boss", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegisterActivity.this, MainFeed.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }else {
                        Toast.makeText(RegisterActivity.this,"Your username or password is incorrect", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    progressDialog.dismiss();
                }
            });
//            call.enqueue(new Callback<ResponseBody>() {
//                @Override
//                public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
//                    progressDialog.dismiss();
//                    String responsevalue = response.toString();
//                    progressBar.setVisibility(View.GONE);
//
//                    Toast.makeText(RegisterActivity.this, " Registeration succesful, we have sent you an email, " +
//                            "pls check your inbox/spam to verify your email ", Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(RegisterActivity.this, MainFeed.class);
//                    startActivity(intent);
//                }
//
//                @Override
//                public void onFailure(Call<ResponseBody> call, Throwable t) {
//                    progressDialog.dismiss();
//                    progressBar.setVisibility(View.GONE);
//                    String responsevalue = t.toString();
//                    Toast.makeText(RegisterActivity.this, "Oga ade you cannot register now " + responsevalue, Toast.LENGTH_LONG).show();
//                }
//            });
//            mAuthTask = new UserLoginTask(email, password);
//            mAuthTask.execute((Void) null);
        }
    }
    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }
    private boolean isUsernameValid(String username) {
        //TODO: Replace this with your own logic
        return username.length() > 4 && username.length()< 10;
    }

    public void startOther(View view){
//        Intent intent = new Intent(MainActivity.this, MainFeed.class);
//        startActivity(intent);
        attemptRegister();


    }
    public void startProfile(View view){
        Intent intent = new Intent(RegisterActivity.this, ProfileActivity.class);
        startActivity(intent);
    }
    public void onboardingstart (View view){
        Intent intent = new Intent(RegisterActivity.this, OnboardingActivity.class);
        startActivity(intent);
    }




}
