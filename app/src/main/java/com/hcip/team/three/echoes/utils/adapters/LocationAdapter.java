package com.hcip.team.three.echoes.utils.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hcip.team.three.echoes.EchoesApplication;
import com.hcip.team.three.echoes.R;

import java.util.ArrayList;

public class LocationAdapter extends BaseAdapter {

    private Context context;
    private EchoesApplication echoesApplication;

    private String[] allLocations;

    public LocationAdapter(Context context, EchoesApplication echoesApplication) {
        this.context = context;
        this.echoesApplication = echoesApplication;

        if (this.echoesApplication.getEchoes().size() == 3) {
            this.allLocations = context.getResources().getStringArray(R.array.locations_first_echo);
        } else {
            this.allLocations = context.getResources().getStringArray(R.array.locations_second_echo);
        }
    }

    @Override
    public int getCount() {
        return allLocations.length;
    }

    @Override
    public Object getItem(int i) {
        return allLocations[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item;

        if (i == 0) {
            item = inflater.inflate(R.layout.custom_layout_current_location, parent, false);
        } else {
            item = inflater.inflate(R.layout.custom_layout_other_location, parent, false);
        }

        try {
            TextView txtLocation = item.findViewById(R.id.location_text);
            txtLocation.setText(allLocations[i]);

            item.setOnClickListener(v -> {
                selectLocation();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return item;
    }

    private void selectLocation() {

    }

}
