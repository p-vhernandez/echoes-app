package com.hcip.team.three.echoes.fragments.creation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hcip.team.three.echoes.EchoesApplication;
import com.hcip.team.three.echoes.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class DateFragment extends Fragment {

    private EchoesApplication echoesApplication;

    private View fragmentView;

    private TextView selectedDate;
    private CalendarView calendarView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_creation_date, parent, false);
        echoesApplication = (EchoesApplication) Objects.requireNonNull(getActivity()).getApplication();

        initialize();

        return fragmentView;
    }

    private void initialize() {
        selectedDate = fragmentView.findViewById(R.id.selected_date);
        calendarView = fragmentView.findViewById(R.id.calendar);

        calendarView.setMaxDate(new Date().getTime());

        setListeners();
    }

    private void setListeners() {
        calendarView.setOnDateChangeListener((calendarView, i, i1, i2) -> {
            try {
                int actualMonth = i1 + 1;
                String selectedDateString = i2 + "/" + actualMonth + "/" + i;
                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date selectedDate = simpleDateFormat.parse(selectedDateString);

                this.selectedDate.setText(echoesApplication.stringFromDateCreation(selectedDate));
                echoesApplication.saveEchoDate(selectedDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

}
