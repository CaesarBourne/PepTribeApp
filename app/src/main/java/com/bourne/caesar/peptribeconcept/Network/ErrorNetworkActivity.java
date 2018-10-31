package com.bourne.caesar.peptribeconcept.Network;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bourne.caesar.peptribeconcept.MainFeed;
import com.bourne.caesar.peptribeconcept.R;

public class ErrorNetworkActivity extends AppCompatActivity {

    public static final String ERROR_NO_INTERNET="nointernet";
    public static final String ERROR_SOMETHING_WRONG = "somethingerror";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_network);
        TextView textError = findViewById(R.id.textViewerror);

        if (getIntent().getExtras().getString(ERROR_NO_INTERNET ) != null){
            textError.setText(getIntent().getExtras().getString(ERROR_NO_INTERNET ));
        }
    }

    public void buttonRetry(View view){

    }
    public void buttonExit(View view){

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Redirect();
    }
    public void Redirect(){
        if (Connection.connectionAvaialable(ErrorNetworkActivity.this)) {

            Intent intent = new Intent(ErrorNetworkActivity.this, MainFeed.class);
            startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(ErrorNetworkActivity.this, "Plaease Check the internet connection", Toast.LENGTH_LONG).show();
        }
    }


}
