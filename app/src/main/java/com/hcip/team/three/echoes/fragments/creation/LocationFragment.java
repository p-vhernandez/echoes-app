package com.hcip.team.three.echoes.fragments.creation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hcip.team.three.echoes.EchoesApplication;
import com.hcip.team.three.echoes.R;
import com.hcip.team.three.echoes.utils.LocationWatcher;
import com.hcip.team.three.echoes.utils.adapters.LocationAdapter;

import java.util.Objects;

public class LocationFragment extends Fragment {

    private EchoesApplication echoesApplication;

    private View fragmentView;
    private ListView locationList;

    private LocationAdapter locationAdapter;
    private EditText inputLocation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_creation_location, parent, false);
        echoesApplication = (EchoesApplication) Objects.requireNonNull(getActivity()).getApplication();

        initialize();

        return fragmentView;
    }

    private void initialize() {
        locationList = fragmentView.findViewById(R.id.locations_list);
        inputLocation = fragmentView.findViewById(R.id.location_input);

        setUpAdapter();
        setListeners();
    }

    private void setUpAdapter() {
        locationAdapter = new LocationAdapter(requireContext(), echoesApplication);
        locationList.setAdapter(locationAdapter);
    }

    private void setListeners() {
        inputLocation.addTextChangedListener(new LocationWatcher(locationAdapter));
    }

}
