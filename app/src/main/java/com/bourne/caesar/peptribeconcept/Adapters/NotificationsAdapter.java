package com.bourne.caesar.peptribeconcept.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bourne.caesar.peptribeconcept.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotificationsViewHolder> {

    ArrayList<String> requestFriendName;
    ArrayList<String> requestFriendPicture;
    ArrayList<String> notificateActiontext;
    ArrayList<String> notificateTimeText;

    public NotificationsAdapter(ArrayList<String> requestFriendName, ArrayList<String> requestFriendPicture, ArrayList<String> notificateActiontext, ArrayList<String> notificateTime) {
        this.requestFriendName = requestFriendName;
        this.requestFriendPicture = requestFriendPicture;
        this.notificateActiontext = notificateActiontext;
        this.notificateTimeText = notificateTime;

    }

//    public NotificationsAdapter(ArrayList<String> requestFriendPicture, ArrayList<String> requestFriendName) {
//        this.requestFriendName = requestFriendName;
//        this.requestFriendPicture = requestFriendPicture;
//
//    }

    @NonNull
    @Override
    public NotificationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_rows, parent, false);
        return new NotificationsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsViewHolder notificationsViewHolder, int position) {

        String requestname = requestFriendName.get(position);
        String requestpic = requestFriendPicture.get(position);
        Picasso.get().load(requestpic).into(notificationsViewHolder.friendImage);
        notificationsViewHolder.userText.setText(requestname);
        notificationsViewHolder.notificationAction.setText(notificateActiontext.get(position));
        notificationsViewHolder.notificationtime.setText(notificateTimeText.get(position));
    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public class NotificationsViewHolder extends RecyclerView.ViewHolder{
        CircleImageView friendImage;
        TextView userText, notificationAction, notificationtime;

    public NotificationsViewHolder(View view) {
        super(view);
        friendImage = view.findViewById(R.id.requestfrienddp);
        userText = view.findViewById(R.id.requestFriendName);
        notificationAction = view.findViewById(R.id.notifAction);
        notificationtime = view.findViewById(R.id.notifiacationtime);
    }


    }
}
