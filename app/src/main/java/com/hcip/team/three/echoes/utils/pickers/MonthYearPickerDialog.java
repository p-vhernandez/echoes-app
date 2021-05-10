package com.hcip.team.three.echoes.utils.pickers;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
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

    public void setListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }

    @NonNull
    @SuppressLint("ResourceAsColor")
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()), R.style.PickerDialogStyle);
        LayoutInflater inflater = getActivity().getLayoutInflater();

        Calendar cal = Calendar.getInstance();

        View dialog = inflater.inflate(R.layout.custom_layout_picker_dialog, null);
        final NumberPicker dayPicker = dialog.findViewById(R.id.picker_day);
        final NumberPicker monthPicker = dialog.findViewById(R.id.picker_month);
        final NumberPicker yearPicker = dialog.findViewById(R.id.picker_year);
        final Button pbutton = dialog.findViewById(R.id.button_ok);
        final Button nbutton = dialog.findViewById(R.id.button_cancel);

        monthPicker.setMinValue(1);
        monthPicker.setMaxValue(12);
        monthPicker.setValue(cal.get(Calendar.MONTH) + 1);

        int year = cal.get(Calendar.YEAR);
        yearPicker.setMinValue(1900);
        yearPicker.setMaxValue(year);
        yearPicker.setValue(year);


        builder.setView(dialog);
        pbutton.setOnClickListener(view -> listener.onDateSet(null, yearPicker.getValue(), monthPicker.getValue(), dayPicker.getValue()));
        nbutton.setOnClickListener(view -> Objects.requireNonNull(MonthYearPickerDialog.this.getDialog()).cancel());

        return builder.create();
    }

    public static boolean isLeapYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
    }

}
