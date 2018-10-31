package com.bourne.caesar.peptribeconcept;


import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OnboardingActivity extends AppCompatActivity {

    ViewPager sliderviewpager;
    SliderAdapter sliderAdapter;
    LinearLayout myDotsLinearLayout;
    TextView  [] mdots;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        sliderviewpager = (ViewPager) findViewById(R.id.slideview);
        sliderAdapter = new SliderAdapter(this);
        sliderviewpager.setAdapter(sliderAdapter);
        myDotsLinearLayout = (LinearLayout) findViewById(R.id.layoutslide);
        addDotsIndicator();
    }

    public void addDotsIndicator(){


        mdots = new TextView[3];

        for (int i = 0; i < mdots.length; i++){
            mdots[i] = new TextView(this);
            mdots[i].setText(Html.fromHtml("&#8226;"));
            mdots[i].setTextSize(35);
            mdots[i].setTextColor(getResources().getColor(R.color.white_transparent));
            myDotsLinearLayout.addView(mdots[i]);
        }

    }
}
