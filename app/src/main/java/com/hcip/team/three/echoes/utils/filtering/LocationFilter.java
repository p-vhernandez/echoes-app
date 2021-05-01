package com.hcip.team.three.echoes.utils.filtering;

import android.widget.Filter;

import com.hcip.team.three.echoes.utils.adapters.LocationAdapter;

import java.util.ArrayList;
import java.util.List;

public class LocationFilter extends Filter {

    private LocationAdapter locationAdapter;
    private List<String> locations;

    public LocationFilter(LocationAdapter locationAdapter, List<String> locations) {
        this.locationAdapter = locationAdapter;
        this.locations = locations;
    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        String filterLocation = charSequence.toString().toLowerCase();
        FilterResults results = new FilterResults();
        final ArrayList<String> tempLocations = new ArrayList<>();

        for (int i = 0; i < locations.size(); i++) {
            if (locations.get(i).toLowerCase().contains(filterLocation)) {
                tempLocations.add(locations.get(i));
            }
        }

        results.values = tempLocations;
        results.count = tempLocations.size();

        return results;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        locationAdapter.setFileteredLocations((List<String>) filterResults.values);
        locationAdapter.notifyDataSetChanged();
    }
}
