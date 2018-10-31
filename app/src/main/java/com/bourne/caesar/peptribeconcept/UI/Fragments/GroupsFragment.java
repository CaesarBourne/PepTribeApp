package com.bourne.caesar.peptribeconcept.UI.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bourne.caesar.peptribeconcept.Adapters.NotificationsAdapter;
import com.bourne.caesar.peptribeconcept.Constants.Constants;
import com.bourne.caesar.peptribeconcept.R;
import com.bourne.caesar.peptribeconcept.Retrofit.CustomResponses.GeneralDataResponse;
import com.bourne.caesar.peptribeconcept.Retrofit.RetrofitClient;
import com.bourne.caesar.peptribeconcept.Storage.SharedPreferencesManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class GroupsFragment extends Fragment {


    public GroupsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_groups, container, false);
        final TextView actionT, name, time;
        final CircleImageView circleImageView = view.findViewById(R.id.requestfrienddp);
         actionT = view.findViewById(R.id.notifAction);
         name = view.findViewById(R.id.requestFriendName);
         time = view.findViewById(R.id.notifiacationtime);

        String usertoken = SharedPreferencesManager.getSharedPrefInstance(getActivity()).getToken(Constants.TOKEN_SAVED_USER);
        final String notifications,friend_requests,pro_users,promoted_pages,count_new_messages;
        final String notification = "notifications";
        Call<GeneralDataResponse> generalDataResponseCall =  RetrofitClient
                .getInstance().getApi().getNotifications(usertoken, Constants.VALUE_SERVER_KEY, notification);

        generalDataResponseCall.enqueue(new Callback<GeneralDataResponse>() {
            @Override
            public void onResponse(Call<GeneralDataResponse> call, Response<GeneralDataResponse> response) {
                if (response.isSuccessful()){
                    GeneralDataResponse generalDataResponse = response.body();

                    //add all the notification action type types
                    for (int i = 0; i < generalDataResponse.getNotifications().size(); i++){
                        String  notifieraction =  generalDataResponse.getNotifications().get(i).getType_text();
                        actionT.setText(notifieraction);
                    }
                    //add the Time of notification
                    for (int i = 0; i < generalDataResponse.getNotifications().size(); i++){
                        String  notifiertime =  generalDataResponse.getNotifications().get(i).getTime_text_string();
                        time.setText(notifiertime);
                    }
                    //add the notificationPhoto
                    for (int i = 0; i < generalDataResponse.getNotifications().size(); i++){
                        String  notificatePhoto =  generalDataResponse.getNotifications().get(i).getNotifier().getAvatar();
                        Picasso.get().load(notificatePhoto).into(circleImageView);
                    }
                    //add the notificationfriendName
                    for (int i = 0; i < generalDataResponse.getNotifications().size(); i++){
                        String  notifierName =  generalDataResponse.getNotifications().get(i).getNotifier().getUsername();
                        name.setText(notifierName);
                    }



                }
            }

            @Override
            public void onFailure(Call<GeneralDataResponse> call, Throwable t) {

            }
        });
        return view ;

    }

}
