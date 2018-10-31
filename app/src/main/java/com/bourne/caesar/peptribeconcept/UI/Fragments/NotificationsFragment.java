package com.bourne.caesar.peptribeconcept.UI.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bourne.caesar.peptribeconcept.Adapters.NotificationsAdapter;
import com.bourne.caesar.peptribeconcept.Constants.Constants;
import com.bourne.caesar.peptribeconcept.R;
import com.bourne.caesar.peptribeconcept.Retrofit.CustomResponses.GeneralDataResponse;
import com.bourne.caesar.peptribeconcept.Retrofit.RetrofitClient;
import com.bourne.caesar.peptribeconcept.Storage.SharedPreferencesManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
// * {@l
 * to handle interaction events.
 * Use the {@link NotificationsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    NotificationsAdapter notificationsAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public NotificationsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificationsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotificationsFragment newInstance(String param1, String param2) {
        NotificationsFragment fragment = new NotificationsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        final RecyclerView notifRecyclerView;

        notifRecyclerView = view.findViewById(R.id.recyclerViewNotifications);
        notifRecyclerView.setHasFixedSize(true);
        LinearLayoutManager notifLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        notifRecyclerView.setLayoutManager(notifLinearLayoutManager);



        String usertoken = SharedPreferencesManager.getSharedPrefInstance(getActivity()).getToken(Constants.TOKEN_SAVED_USER);
        final String notifications,friend_requests,pro_users,promoted_pages,count_new_messages;
        final String notification = "notifications";
        Call<GeneralDataResponse> generalDataResponseCall =  RetrofitClient
                .getInstance().getApi().getNotifications(usertoken, Constants.VALUE_SERVER_KEY, notification);

//create arraylists to be added to get data from response and add them to adapter
        final ArrayList<String> action = new ArrayList<>();
        final ArrayList<String> NotificateTime = new ArrayList<>();
        final ArrayList<String> NotificatePhoto = new ArrayList<>();
        final ArrayList<String> NotificationFriendName = new ArrayList<>();
        generalDataResponseCall.enqueue(new Callback<GeneralDataResponse>() {
            @Override
            public void onResponse(Call<GeneralDataResponse> call, Response<GeneralDataResponse> response) {
                if (response.isSuccessful()){
                    GeneralDataResponse generalDataResponse = response.body();

                    //add all the notification action type types
                    for (int i = 0; i < generalDataResponse.getNotifications().size(); i++){
                        String  notifieraction =  generalDataResponse.getNotifications().get(i).getType_text();
                        action.add(notifieraction);
                    }
        //add the Time of notification
                    for (int i = 0; i < generalDataResponse.getNotifications().size(); i++){
                        String  notifiertime =  generalDataResponse.getNotifications().get(i).getTime_text_string();
                        NotificateTime.add(notifiertime);
                    }
        //add the notificationPhoto
                    for (int i = 0; i < generalDataResponse.getNotifications().size(); i++){
                      String  notificatePhoto =  generalDataResponse.getNotifications().get(i).getNotifier().getAvatar();
                        NotificatePhoto.add(notificatePhoto);
                    }
        //add the notificationfriendName
                    for (int i = 0; i < generalDataResponse.getNotifications().size(); i++){
                        String  notifiertime =  generalDataResponse.getNotifications().get(i).getNotifier().getUsername();
                        NotificationFriendName.add(notifiertime);
                    }

                    notificationsAdapter = new NotificationsAdapter(NotificationFriendName,
                            NotificatePhoto,action, NotificateTime);
                    notifRecyclerView.setAdapter(notificationsAdapter);

                }
            }

            @Override
            public void onFailure(Call<GeneralDataResponse> call, Throwable t) {

            }
        });

        return view;
    }



}
