package com.bourne.caesar.peptribeconcept;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bourne.caesar.peptribeconcept.Constants.Constants;
import com.bourne.caesar.peptribeconcept.Retrofit.CustomResponses.CookieTestResponse;
import com.bourne.caesar.peptribeconcept.Retrofit.RetrofitClient;
import com.bourne.caesar.peptribeconcept.Storage.SharedPreferencesManager;
import com.bourne.caesar.peptribeconcept.UI.Fragments.GroupsFragment;
import com.bourne.caesar.peptribeconcept.UI.Fragments.NewsFeedFragment;
import com.bourne.caesar.peptribeconcept.UI.Fragments.NotificationsFragment;
import com.bourne.caesar.peptribeconcept.UI.Fragments.ProfileFragment;
import com.mancj.materialsearchbar.MaterialSearchBar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFeed extends AppCompatActivity implements MaterialSearchBar.OnSearchActionListener  {

    private static final String TAG = "MainFeed";
    MaterialSearchBar searchBar;
    private TextView mTextMessage;
//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//        }
//    };

    @Override
    protected void onStart() {
        super.onStart();
        if (!SharedPreferencesManager.getSharedPrefInstance(MainFeed.this).isLoggedIn()){
//            Log.v(TAG,"This is the User Id " + userID);
            Toast.makeText(MainFeed.this, "Userid not saved go back to login",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainFeed.this, MainFeed.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_feed);

        String usertoken = SharedPreferencesManager.getSharedPrefInstance(this).getToken(Constants.TOKEN_SAVED_USER);
        int userId =  SharedPreferencesManager.getSharedPrefInstance(this).getUserId(Constants.USER_ID);

//
        Call<CookieTestResponse> browserCookie  = RetrofitClient.getInstance().getApi().getBrowserCookie(
                Constants.VALUE_SERVER_KEY,
                usertoken);

        browserCookie.enqueue(new Callback<CookieTestResponse>() {
            @Override
            public void onResponse(Call<CookieTestResponse> call, Response<CookieTestResponse> response) {
                if (response.isSuccessful()){
//                    String cookie  = response.headers().get("Set-Cookie");
//                    String html = response.body().toString();

//                    shwhtml.setText(cookie);
//                    webViewPep.loadData(html, "text/html", "UTF-8");
//                    Toast.makeText(getActivity(), "Cookies response is succesful", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<CookieTestResponse> call, Throwable t) {

//                shwhtml.setText(call.toString()+" no response "+ t.toString());
//                Toast.makeText(getActivity(), "Oaga nothing they show for cookies", Toast.LENGTH_LONG).show();
            }
        });


        SectionsPageAdapter sectionAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpage);
        TabLayout myTablayout = (TabLayout) findViewById(R.id.myTablayout);
        myTablayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(sectionAdapter);

        myTablayout.getTabAt(0).setIcon(R.drawable.homewhite);
        myTablayout.getTabAt(2).setIcon(R.drawable.notificationtab);
        myTablayout.getTabAt(1).setIcon(R.drawable.groups);
        myTablayout.getTabAt(3).setIcon(R.drawable.togglemore);


        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent intent = new Intent(MainFeed.this, MainFeed.class);
                        startActivity(intent);
                        return true;
                    case R.id.navigation_dashboard:
                        Intent intent2 = new Intent(MainFeed.this, ProfileActivity.class);
                        startActivity(intent2);
                        return true;
                    case R.id.navigation_notifications:
                        Intent intent3 = new Intent(MainFeed.this, OnboardingActivity.class);
                        startActivity(intent3);
                        return true;
                }
                return false;
            }
        });
        searchBar = (MaterialSearchBar) findViewById(R.id.searchBar);
        searchBar.setOnSearchActionListener(this);
        searchBar.inflateMenu(R.menu.main);
        searchBar.setText("Hello World!");
        Log.d("LOG_TAG", getClass().getSimpleName() + ": text " + searchBar.getText());
        searchBar.setCardViewElevation(10);
        searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d("LOG_TAG", getClass().getSimpleName() + " text changed " + searchBar.getText());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });

    }

    public class SectionsPageAdapter extends FragmentPagerAdapter {
        public SectionsPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new NewsFeedFragment();

                case 1:
                    return new GroupsFragment();

                case 2:
                    return new ProfileFragment();

                case 3:
                    return new NotificationsFragment();

            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
//            switch (position){
//                case 0:
//                    return getResources().getString(R.string.Home);
//                case 1:
//                    return getResources().getString(R.string.Eatery);
//                case 2:
//                    return getResources().getString(R.string.Supermarket);
//                case 3:
//                    return getResources().getString(R.string.Hotel);
//            }
            return null;
        }
    }

    @Override
    public void onSearchStateChanged(boolean enabled) {
    }

    @Override
    public void onSearchConfirmed(CharSequence text) {

    }

    @Override
    public void onButtonClicked(int buttonCode) {
        switch (buttonCode){
            case MaterialSearchBar.BUTTON_NAVIGATION:
//                drawer.openDrawer(Gravity.LEFT);
                break;
            case MaterialSearchBar.BUTTON_SPEECH:
                break;
            case MaterialSearchBar.BUTTON_BACK:
                searchBar.disableSearch();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu) ;

    }

    public void logOutUser(){

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
//            case R.id.createorder:
//                Intent intent = new Intent(this, TestPayStack.class);
//                startActivity(intent);
//                return true;
            case R.id.logoutUser:
//                startAlertDialogLogout();
                return true;
            default
                    :return super.onOptionsItemSelected(item);
        }

    }

    public void startAlertDialogLogout(View view){
        new AlertDialog.Builder(this).setTitle("Logout")
                .setMessage("Are you sure you want to Logout")
                .setIcon(R.drawable.logout_warning)
                .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        logoutUser();
                        Intent intent = new Intent(MainFeed.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }

    public void logoutUser(){
        SharedPreferencesManager.getSharedPrefInstance(this).clearLogout();
    }

}
