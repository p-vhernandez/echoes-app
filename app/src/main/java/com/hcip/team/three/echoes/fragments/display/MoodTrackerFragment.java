package com.hcip.team.three.echoes.fragments.display;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hcip.team.three.echoes.EchoesApplication;
import com.hcip.team.three.echoes.R;
import com.hcip.team.three.echoes.activities.DetailsActivity;
import com.hcip.team.three.echoes.utils.pickers.DayMonthYearPickerDialog;
import com.hcip.team.three.echoes.utils.pickers.MonthYearPickerDialog;
import com.hcip.team.three.echoes.utils.pickers.YearPickerDialog;

import java.text.SimpleDateFormat;
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

    private View saddestEcho;
    private View happiesttEcho;

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

        saddestEcho = fragmentView.findViewById(R.id.saddest_moment);
        happiesttEcho = fragmentView.findViewById(R.id.happiest_moment);

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
                        changeImages(TXT_MONTHLY);
                        break;
                    case 2:
                        changeButtonsText(TXT_DAILY);
                        changeImages(TXT_DAILY);
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
                    createYearDialog().show(Objects.requireNonNull(getFragmentManager()), "MonthYearPickerDialog");
                    break;
                case TXT_MONTHLY:
                    createMonthYearDialog().show(Objects.requireNonNull(getFragmentManager()), "MonthYearPickerDialog");
                    break;
                case TXT_DAILY:
                    createDayMonthYearDialog().show(Objects.requireNonNull(getFragmentManager()), "MonthYearPickerDialog");
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

    @SuppressLint("UseCompatLoadingForDrawables")
    private void changeImages(String type) {
        switch (type) {
            case TXT_YEARLY:
                moodStats.setImageDrawable(getResources().getDrawable(R.drawable.img_your_moods));
                moodGraph.setImageDrawable(getResources().getDrawable(R.drawable.img_mood_distribution));
                moodHighlights.setImageDrawable(getResources().getDrawable(R.drawable.img_highlights));
                break;
            case TXT_MONTHLY:
                if (current.get(Calendar.MONTH) == Calendar.FEBRUARY) {
                    moodStats.setImageDrawable(getResources().getDrawable(R.drawable.img_your_moods_feb));
                    moodGraph.setImageDrawable(getResources().getDrawable(R.drawable.img_mood_distribution_month_feb));
                    moodHighlights.setImageDrawable(getResources().getDrawable(R.drawable.img_highlights_month_feb));

                    setHighlightListener(true);
                } else {
                    moodStats.setImageDrawable(getResources().getDrawable(R.drawable.img_your_moods_month));
                    moodGraph.setImageDrawable(getResources().getDrawable(R.drawable.img_mood_distribution_month));
                    moodHighlights.setImageDrawable(getResources().getDrawable(R.drawable.img_highlights_month));

                    setHighlightListener(false);
                }
                break;
            case TXT_DAILY:
                // not prepared
                break;
        }
    }

    private void setHighlightListener(boolean clickable) {
        if (clickable) {
            saddestEcho.setOnClickListener(view -> goToSaddestEcho());
            happiesttEcho.setOnClickListener(view -> goToHappiestEcho());
        } else {
            saddestEcho.setOnClickListener(null);
            happiesttEcho.setOnClickListener(null);
        }
    }

    private void goToSaddestEcho() {
        Intent intent = new Intent(getContext(), DetailsActivity.class);
        intent.putExtra("echo", "saddest");
        Objects.requireNonNull(getActivity()).startActivity(intent);
    }

    private void goToHappiestEcho() {
        Intent intent = new Intent(getContext(), DetailsActivity.class);
        intent.putExtra("echo", "happiest");
        Objects.requireNonNull(getActivity()).startActivity(intent);
    }

    private DayMonthYearPickerDialog createDayMonthYearDialog() {
        DayMonthYearPickerDialog newFragment = new DayMonthYearPickerDialog();

        newFragment.setListener((datePicker, selectedYear, selectedMonth, selectedDate) -> {
            current.set(Calendar.YEAR, selectedYear);
            current.set(Calendar.MONTH, selectedMonth - 1);
            current.set(Calendar.DAY_OF_MONTH, selectedDate);
            newFragment.dismiss();
            changeButtonsText(timeSlotSelected);
            changeImages(timeSlotSelected);
        });

        return newFragment;
    }

    private MonthYearPickerDialog createMonthYearDialog() {
        MonthYearPickerDialog newFragment = new MonthYearPickerDialog();

        newFragment.setListener((datePicker, selectedYear, selectedMonth, selectedDate) -> {
            current.set(Calendar.YEAR, selectedYear);
            current.set(Calendar.MONTH, selectedMonth - 1);
            current.set(Calendar.DAY_OF_MONTH, selectedDate);
            newFragment.dismiss();
            changeButtonsText(timeSlotSelected);
            changeImages(timeSlotSelected);
        });

        return newFragment;
    }

    private YearPickerDialog createYearDialog() {
        YearPickerDialog newFragment = new YearPickerDialog();

        newFragment.setListener((datePicker, selectedYear, selectedMonth, selectedDate) -> {
            current.set(Calendar.YEAR, selectedYear);
            current.set(Calendar.MONTH, selectedMonth - 1);
            current.set(Calendar.DAY_OF_MONTH, selectedDate);
            newFragment.dismiss();
            changeButtonsText(timeSlotSelected);
            changeImages(timeSlotSelected);
        });

        return newFragment;
    }

}
