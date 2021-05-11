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

public class YearPickerDialog extends DialogFragment {

    private DatePickerDialog.OnDateSetListener listener;

    private int daysOfMonth = 31;

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

        View dialog = inflater.inflate(R.layout.custom_layout_year_picker_dialog, null);

        yearPicker = dialog.findViewById(R.id.picker_year);

        final Button pbutton = dialog.findViewById(R.id.button_ok);
        final Button nbutton = dialog.findViewById(R.id.button_cancel);

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
        pbutton.setOnClickListener(view -> listener.onDateSet(null, yearPicker.getValue(), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)));
        nbutton.setOnClickListener(view -> Objects.requireNonNull(YearPickerDialog.this.getDialog()).cancel());

        return builder.create();
    }

    public static boolean isLeapYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
    }

}
