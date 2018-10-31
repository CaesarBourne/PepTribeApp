package com.bourne.caesar.peptribeconcept.UI.Fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bourne.caesar.peptribeconcept.Constants.Constants;
import com.bourne.caesar.peptribeconcept.R;
import com.bourne.caesar.peptribeconcept.Retrofit.CustomResponses.UserDataResponse;

import com.bourne.caesar.peptribeconcept.Retrofit.CustomResponses.UserInfo;
import com.bourne.caesar.peptribeconcept.Retrofit.RetrofitClient;
import com.bourne.caesar.peptribeconcept.Storage.SharedPreferencesManager;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Dialog myDialog;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
     String avatarDp, emailValue, usernameValue, coverpicturevalue;


CircleImageView circleImageView;
ImageView coverphoto;
TextView emailText, UsernameText;

    public ProfileFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDialog = new Dialog(getActivity());
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        circleImageView = view.findViewById(R.id.profiledp);
        coverphoto = view.findViewById(R.id.coverphoto);
        emailText = view.findViewById(R.id.email);
        UsernameText = view.findViewById(R.id.USERNAME);

        String usertoken = SharedPreferencesManager.getSharedPrefInstance(getActivity()).getToken(Constants.TOKEN_SAVED_USER);
        int userId =  SharedPreferencesManager.getSharedPrefInstance(getActivity()).getUserId(Constants.USER_ID);
        Call<UserDataResponse> callUser = RetrofitClient.getInstance().getApi().getUserData(
                usertoken,
                userId,
                Constants.USER_DATA,
                Constants.VALUE_SERVER_KEY
        );
        callUser.enqueue(new Callback<UserDataResponse>() {
            @Override
            public void onResponse(Call<UserDataResponse> call, Response<UserDataResponse> response) {
                if (response.isSuccessful()){
                    UserInfo myuserData = response.body().getUser_data();

                    avatarDp = myuserData.getAvatar();
                    emailValue = myuserData.getEmail();
                    usernameValue = myuserData.getUsername();
                    coverpicturevalue = myuserData.getCover();

                    Picasso.get().load(avatarDp).into(circleImageView);
                    Picasso.get().load(coverpicturevalue).into(coverphoto);
//        Glide.with(getActivity())
//                .asBitmap()
//                .load(avatarDp)
//                .into(circleImageView);
                    UsernameText.setText(usernameValue);
                    emailText.setText(emailValue);

                }
            }

            @Override
            public void onFailure(Call<UserDataResponse> call, Throwable t) {

            }
        });




        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtclose;
                Button btnFollow;
                myDialog.setContentView(R.layout.custompopup);
                txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
                txtclose.setText("X");
                btnFollow = (Button) myDialog.findViewById(R.id.btnfollow);
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();
            }
        });
        return view ;
    }


   }
