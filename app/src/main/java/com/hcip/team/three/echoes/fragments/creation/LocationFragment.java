package com.hcip.team.three.echoes.fragments.creation;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.hcip.team.three.echoes.EchoesApplication;
import com.hcip.team.three.echoes.R;
import com.hcip.team.three.echoes.utils.filtering.LocationWatcher;
import com.hcip.team.three.echoes.utils.adapters.LocationAdapter;
import com.hcip.team.three.echoes.utils.interfaces.LocationSelectedListener;

import java.util.Objects;

public class LocationFragment extends Fragment implements LocationSelectedListener {

    private EchoesApplication echoesApplication;

    private View fragmentView;
    private ListView locationList;

    private LocationAdapter locationAdapter;
    private EditText inputLocation;

    private RelativeLayout locationSearchLayout;
    private ConstraintLayout locationSelectedLayout;

    private ImageView imgExit;
    private TextView selectedLocation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_creation_location, parent, false);
        echoesApplication = (EchoesApplication) Objects.requireNonNull(getActivity()).getApplication();

        initialize();

        return fragmentView;
    }

    private void initialize() {
        locationSearchLayout = fragmentView.findViewById(R.id.location_isnot_selected);
        locationSelectedLayout = fragmentView.findViewById(R.id.location_is_selected);

        locationSearchLayout.setVisibility(View.VISIBLE);
        locationSelectedLayout.setVisibility(View.GONE);

        locationList = fragmentView.findViewById(R.id.locations_list);
        inputLocation = fragmentView.findViewById(R.id.location_input);

        imgExit = fragmentView.findViewById(R.id.exit_icon);
        selectedLocation = fragmentView.findViewById(R.id.selected_location);

        setUpAdapter();
        setListeners();
    }

    private void setUpAdapter() {
        locationAdapter = new LocationAdapter(requireContext(), echoesApplication, this);
        locationList.setAdapter(locationAdapter);
    }

    private void setListeners() {
        inputLocation.addTextChangedListener(new LocationWatcher(locationAdapter));
        imgExit.setOnClickListener(view -> {
            showLocationSearch();
        });
    }

    private void showLocationSearch() {
        echoesApplication.saveEchoLocation(null);
        inputLocation.setText("");
        closeKeyBoard();
        locationSearchLayout.setVisibility(View.VISIBLE);
        locationSelectedLayout.setVisibility(View.GONE);
    }

    private void showSelectedLocation(String location) {
        echoesApplication.saveEchoLocation(location);
        selectedLocation.setText(location);
        locationSearchLayout.setVisibility(View.GONE);
        locationSelectedLayout.setVisibility(View.VISIBLE);
    }

    private void closeKeyBoard() {
//        InputMethodManager manager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//        manager.hideSoftInputFromWindow(getView().getWindowToken(), 0);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    @Override
    public void onLocationSelected(String location) {
        showSelectedLocation(location);
    }
}
