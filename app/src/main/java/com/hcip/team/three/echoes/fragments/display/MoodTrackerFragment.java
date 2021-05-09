package com.hcip.team.three.echoes.fragments.display;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hcip.team.three.echoes.EchoesApplication;
import com.hcip.team.three.echoes.R;

import java.util.Objects;

public class MoodTrackerFragment extends Fragment {

    private EchoesApplication echoesApplication;

    private View fragmentView;

    private String[] timeSlotOptions;
    private ArrayAdapter<String> timeSlotAdapter;
    private Spinner timeSlotSpinner;

    private String timeSlotSelected;

    private Button btnFilterDate;

    private static final String TXT_YEARLY = "Yearly";
    private static final String TXT_MONTHLY = "Monthly";
    private static final String TXT_DAILY = "Daily";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_mood_tracker, parent, false);
        echoesApplication = (EchoesApplication) Objects.requireNonNull(getActivity()).getApplication();

        initialize();

        return fragmentView;
    }

    private void initialize() {
        timeSlotSpinner = fragmentView.findViewById(R.id.year_month_spinner);
        timeSlotOptions = getResources().getStringArray(R.array.year_month_spinner);
        timeSlotAdapter = new ArrayAdapter<>(getActivity(), R.layout.custom_layout_spinner, timeSlotOptions);

        btnFilterDate = fragmentView.findViewById(R.id.filter_date);

        setUpAdapters();
        setUpListeners();
    }

    private void setUpAdapters() {
        timeSlotAdapter.setDropDownViewResource(R.layout.custom_layout_spinner_item);
        timeSlotSpinner.setAdapter(timeSlotAdapter);
    }

    private void setUpListeners() {
        timeSlotSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                timeSlotSelected = timeSlotOptions[i];

                switch (i) {
                    case 0:
                        //TODO
                        break;
                    case 1:
                        //TODO
                        break;
                    case 2:
                        //TODO
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // do nothing
            }
        });

        btnFilterDate.setOnClickListener(view -> {
            switch (timeSlotSelected) {
                case TXT_YEARLY:
                    //TODO
                    break;
                case TXT_MONTHLY:
                    //TODO
                    break;
                case TXT_DAILY:
                    //TODO
                    break;
            }
        });
    }

}
