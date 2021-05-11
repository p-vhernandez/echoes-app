package com.hcip.team.three.echoes.fragments.display;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hcip.team.three.echoes.EchoesApplication;
import com.hcip.team.three.echoes.R;
import com.hcip.team.three.echoes.utils.pickers.MonthYearPickerDialog;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class MoodTrackerFragment extends Fragment {

    private EchoesApplication echoesApplication;

    private View fragmentView;

    private String[] timeSlotOptions;
    private ArrayAdapter<String> timeSlotAdapter;
    private Spinner timeSlotSpinner;

    private String timeSlotSelected;

    private ImageView moodStats;
    private ImageView moodGraph;
    private ImageView moodHighlights;

    private Button btnFilterDate;

    private Calendar current;
    private SimpleDateFormat yearlyFormat;
    private SimpleDateFormat monthlyFormat;
    private SimpleDateFormat dailyFormat;

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

    @SuppressLint("SimpleDateFormat")
    private void initialize() {
        timeSlotSpinner = fragmentView.findViewById(R.id.year_month_spinner);
        timeSlotOptions = getResources().getStringArray(R.array.year_month_spinner);
        timeSlotAdapter = new ArrayAdapter<>(getActivity(), R.layout.custom_layout_spinner, timeSlotOptions);

        btnFilterDate = fragmentView.findViewById(R.id.filter_date);

        current = Calendar.getInstance();
        current.setTime(new Date());
        yearlyFormat = new SimpleDateFormat("yyyy");
        monthlyFormat = new SimpleDateFormat("MMM, yyyy");
        dailyFormat = new SimpleDateFormat("dd MMMM, yyyy");

        moodStats = fragmentView.findViewById(R.id.mood_stats);
        moodGraph = fragmentView.findViewById(R.id.mood_graph);
        moodHighlights = fragmentView.findViewById(R.id.mood_highlights);

        changeButtonsText(TXT_YEARLY);
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
                        changeButtonsText(TXT_YEARLY);
                        changeImages(TXT_YEARLY);
                        break;
                    case 1:
                        changeButtonsText(TXT_MONTHLY);
                        changeImages(TXT_YEARLY);
                        break;
                    case 2:
                        changeButtonsText(TXT_DAILY);
                        changeImages(TXT_YEARLY);
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
                    createDialogWithoutDateField().show(Objects.requireNonNull(getFragmentManager()), "MonthYearPickerDialog");
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

    private void changeButtonsText(String type) {
        switch (type) {
            case TXT_YEARLY:
                btnFilterDate.setText(yearlyFormat.format(current.getTime()));
                break;
            case TXT_MONTHLY:
                btnFilterDate.setText(monthlyFormat.format(current.getTime()));
                break;
            case TXT_DAILY:
                btnFilterDate.setText(dailyFormat.format(current.getTime()));
                break;
        }
    }

    private void changeImages(String type) {
        switch (type) {
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
    }

    private MonthYearPickerDialog createDialogWithoutDateField() {
        MonthYearPickerDialog newFragment = new MonthYearPickerDialog();

        try {
            java.lang.reflect.Field[] datePickerDialogFields = newFragment.getClass().getDeclaredFields();

            for (java.lang.reflect.Field datePickerDialogField : datePickerDialogFields) {
                if (datePickerDialogField.getName().equals("mDatePicker")) {
                    datePickerDialogField.setAccessible(true);
                    DatePicker datePicker = (DatePicker) datePickerDialogField.get(newFragment);
                    java.lang.reflect.Field[] datePickerFields = datePickerDialogField.getType().getDeclaredFields();

                    for (java.lang.reflect.Field datePickerField : datePickerFields) {

                        if ("mDaySpinner".equals(datePickerField.getName())) {
                            datePickerField.setAccessible(true);
                            Object dayPicker = datePickerField.get(datePicker);
                            ((View) Objects.requireNonNull(dayPicker)).setVisibility(View.GONE);
                        }
                    }
                }
            }

            newFragment.setListener((datePicker, selectedYear, selectedMonth, selectedDate) -> {
                current.set(selectedYear, selectedMonth, selectedDate);
                newFragment.dismiss();
                changeButtonsText(timeSlotSelected);
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return newFragment;
    }

}
