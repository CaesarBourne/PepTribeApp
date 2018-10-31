package com.bourne.caesar.peptribeconcept.Retrofit.CustomResponses;

import com.bourne.caesar.peptribeconcept.Retrofit.JsonDesereilizers.NotificationDesereializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GeneralDataResponse {



    ArrayList<Notifications>  notifications;

    public ArrayList<Notifications> getNotifications() {
        return notifications ;
    }
    //    private String new_notifications_count;
//
//    private Notifications[] notifications;
//
//    private FriendRequests[] friend_requests;
//
//    private String new_friend_requests_count;
//
//    public GeneralDataResponse(String new_notifications_count, Notifications[] notifications, FriendRequests[] friend_requests, String new_friend_requests_count) {
//        this.new_notifications_count = new_notifications_count;
//        this.notifications = notifications;
//        this.friend_requests = friend_requests;
//        this.new_friend_requests_count = new_friend_requests_count;
//
//    }
//
//    public String getNew_notifications_count() {
//
//        return new_notifications_count;
//    }
//
//    public Notifications [] getNotifications() {
//
//        return notifications;
//    }
//
//    public FriendRequests[] getFriend_requests() {
//
//        return friend_requests;
//    }
//
//    public String getNew_friend_requests_count() {
//
////        return new_friend_requests_count;
//    }

}
