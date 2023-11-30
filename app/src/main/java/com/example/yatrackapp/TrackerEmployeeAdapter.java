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

public class TrackerEmployeeAdapter extends ArrayAdapter<TrackerEmployee> {

    public TrackerEmployeeAdapter(Context context, List<TrackerEmployee> trackerEmployeeList) {
        super(context, 0, trackerEmployeeList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tracker, parent, false);
        }

        TrackerEmployee trackerEmployee = getItem(position);

        TextView textViewName = convertView.findViewById(R.id.textViewName);
        TextView textViewJob = convertView.findViewById(R.id.textViewJob);

        if (trackerEmployee != null) {
            textViewName.setText(trackerEmployee.getName());
            textViewJob.setText(trackerEmployee.getJob());
        }

        return convertView;
    }
}
