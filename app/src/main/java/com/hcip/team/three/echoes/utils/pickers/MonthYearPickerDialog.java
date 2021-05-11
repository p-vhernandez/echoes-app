package com.hcip.team.three.echoes.utils.pickers;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.hcip.team.three.echoes.R;

import java.util.Calendar;
import java.util.Objects;

public class MonthYearPickerDialog extends DialogFragment {

    private DatePickerDialog.OnDateSetListener listener;

    private int daysOfMonth = 31;

    private NumberPicker monthPicker;
    private NumberPicker yearPicker;

    public void setListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }

    @NonNull
    @SuppressLint({"ResourceAsColor", "InflateParams"})
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()), R.style.PickerDialogStyle);
        LayoutInflater inflater = getActivity().getLayoutInflater();

        Calendar cal = Calendar.getInstance();

        View dialog = inflater.inflate(R.layout.custom_layout_monthyear_picker_dialog, null);

        monthPicker = dialog.findViewById(R.id.picker_month);
        yearPicker = dialog.findViewById(R.id.picker_year);

        final Button pbutton = dialog.findViewById(R.id.button_ok);
        final Button nbutton = dialog.findViewById(R.id.button_cancel);

        monthPicker.setMinValue(1);
        monthPicker.setMaxValue(12);
        monthPicker.setValue(cal.get(Calendar.MONTH) + 1);
        monthPicker.setDisplayedValues(new String[]{"Jan", "Feb", "Mar", "Apr", "May", "June", "July",
                "Aug", "Sep", "Oct", "Nov", "Dec"});

        monthPicker.setOnValueChangedListener((picker, oldVal, newVal) -> {
            switch (newVal) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    daysOfMonth = 31;
                    break;
                case 2:
                    daysOfMonth = 28;
                    break;

                case 4:
                case 6:
                case 9:
                case 11:
                    daysOfMonth = 30;
                    break;
            }

        });

        int year = cal.get(Calendar.YEAR);
        yearPicker.setMinValue(1900);
        yearPicker.setMaxValue(year);
        yearPicker.setValue(year);

        yearPicker.setOnValueChangedListener((picker, oldVal, newVal) -> {
            try {
                if (isLeapYear(picker.getValue())) {
                    daysOfMonth = 29;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        builder.setView(dialog);
        pbutton.setOnClickListener(view -> listener.onDateSet(null, yearPicker.getValue(), monthPicker.getValue(), cal.get(Calendar.DAY_OF_MONTH)));
        nbutton.setOnClickListener(view -> Objects.requireNonNull(MonthYearPickerDialog.this.getDialog()).cancel());

        return builder.create();
    }

    public static boolean isLeapYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
    }

}
