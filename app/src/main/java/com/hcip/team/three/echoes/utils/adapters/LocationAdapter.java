package com.hcip.team.three.echoes.utils.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.hcip.team.three.echoes.EchoesApplication;
import com.hcip.team.three.echoes.R;
import com.hcip.team.three.echoes.utils.filtering.LocationFilter;
import com.hcip.team.three.echoes.utils.interfaces.LocationSelectedListener;

import java.util.Arrays;
import java.util.List;

public class LocationAdapter extends BaseAdapter implements Filterable {

    private final Context context;
    private final EchoesApplication echoesApplication;

    private final List<String> allLocations;
    private List<String> fileteredLocations;

    private final LocationFilter locationFilter;

    private final boolean firstTask;

    private LocationSelectedListener locationSelectedListener;

    public LocationAdapter(Context context, EchoesApplication echoesApplication, LocationSelectedListener locationSelectedListener) {
        this.context = context;
        this.echoesApplication = echoesApplication;
        this.locationSelectedListener = locationSelectedListener;

        if (this.echoesApplication.getEchoes().size() == 3) {
            this.allLocations = Arrays.asList(context.getResources().getStringArray(R.array.locations_first_echo));
            this.firstTask = true;
        } else {
            this.allLocations = Arrays.asList(context.getResources().getStringArray(R.array.locations_second_echo));
            this.firstTask = false;
        }

        this.fileteredLocations = allLocations;
        this.locationFilter = new LocationFilter(this, allLocations);
    }

    @Override
    public int getCount() {
        return fileteredLocations.size();
    }

    @Override
    public Object getItem(int i) {
        return fileteredLocations.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item;

        if ((firstTask && fileteredLocations.get(i).equals("Palawan, Philippines")) ||
                (!firstTask && fileteredLocations.get(i).equals("Madrid, Spain"))) {
            item = inflater.inflate(R.layout.custom_layout_current_location, parent, false);
        } else {
            item = inflater.inflate(R.layout.custom_layout_other_location, parent, false);
        }

        try {
            TextView txtLocation = item.findViewById(R.id.location_text);
            txtLocation.setText(fileteredLocations.get(i));

            item.setOnClickListener(v -> selectLocation(fileteredLocations.get(i)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return item;
    }

    private void selectLocation(String selectedLocation) {
        locationSelectedListener.onLocationSelected(selectedLocation);
    }

    public void setFileteredLocations(List<String> fileteredLocations) {
        this.fileteredLocations = fileteredLocations;
    }

    @Override
    public Filter getFilter() {
        return locationFilter;
    }

}
