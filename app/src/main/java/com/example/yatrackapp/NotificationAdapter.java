package com.example.yatrackapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class NotificationAdapter extends ArrayAdapter<Notification> {
    public NotificationAdapter(Context context, List<Notification> notificationList) {
        super(context, R.layout.item_notification, notificationList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_notification, null);
        }

        // Set your data to the TextView or other views in the custom layout
        TextView textView = view.findViewById(R.id.tv_notification);
        textView.setText(getItem(position).getNotifText());

        return view;
    }
}
