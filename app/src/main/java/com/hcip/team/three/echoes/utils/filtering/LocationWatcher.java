package com.hcip.team.three.echoes.utils.filtering;

import android.text.Editable;
import android.text.TextWatcher;

import com.hcip.team.three.echoes.utils.adapters.LocationAdapter;

public class LocationWatcher implements TextWatcher {

    private final LocationAdapter locationAdapter;

    public LocationWatcher(LocationAdapter locationAdapter) {
        this.locationAdapter = locationAdapter;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        locationAdapter.getFilter().filter(editable.toString().toLowerCase());
    }
}
