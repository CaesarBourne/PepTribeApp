package com.bourne.caesar.peptribeconcept;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SliderAdapter extends PagerAdapter {

  Context myContext;
  LayoutInflater layoutInflater;

  public SliderAdapter(Context myContext) {
    this.myContext = myContext;
  }

  public int[] sliderImages = {
          R.drawable.linkedin,
          R.drawable.facebook,
          R.drawable.twitter
  };

  public String[] sliderTitles = {
          "Welcome",
          "Like and make Money",
          "Thanks for Patronage"
  };

  public String[] sliderDescriptions = {
          "PEPTribe is a fun social media app that makes you connect with friends and family and make money from different activities like liking",
          "PEPTribe is a fun social media app that makes you connect with friends and family and make money from different activities like liking",
          "PEPTribe is a fun social media app that makes you connect with friends and family and make money from different activities like liking"
  };

  @Override
  public int getCount() {
    return sliderTitles.length;
  }

  @Override
  public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
    return view == (RelativeLayout)o;
  }

  @NonNull
  @Override
  public Object instantiateItem(@NonNull ViewGroup container, int position) {
    layoutInflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View view = layoutInflater.inflate(R.layout.activity_slider_adapter, container, false);

    ImageView slideImageView = (ImageView) view.findViewById(R.id.slideimageView);
    TextView slideTitle = (TextView) view.findViewById(R.id.slidTitle);
    TextView slideDescription = (TextView) view.findViewById(R.id.slideDescription);

    slideImageView.setImageResource(sliderImages[position]);
    slideTitle.setText(sliderTitles[position]);
    slideDescription.setText(sliderDescriptions[position]);

    container.addView(view);

    return view;
  }

  @Override
  public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
    container.removeView((RelativeLayout) object);
  }
}
