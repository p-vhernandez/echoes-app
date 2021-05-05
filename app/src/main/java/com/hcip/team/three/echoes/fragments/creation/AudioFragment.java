package com.hcip.team.three.echoes.fragments.creation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.chip.Chip;
import com.hcip.team.three.echoes.EchoesApplication;
import com.hcip.team.three.echoes.R;

import java.text.SimpleDateFormat;
import java.util.Objects;

public class AudioFragment extends Fragment {

    private EchoesApplication echoesApplication;

    private View fragmentView;

    private ImageView inactiveRecording;
    private ImageView activeRecordingFirst;
    private ImageView activeRecordingSecond;
    private ImageView startRecording;
    private ImageView stopRecording;

    private TextView recordingGuidance;
//    private TextView recordingTimer;

    private Chronometer chronometer;
    private Long timerValue;
    private String timerFormat;
    private Chip recordingTimer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_creation_audio, parent, false);
        echoesApplication = (EchoesApplication) Objects.requireNonNull(getActivity()).getApplication();

        initialize();

        return fragmentView;
    }

    private void initialize() {
        inactiveRecording = fragmentView.findViewById(R.id.recorder_ring_inactive);
        activeRecordingFirst = fragmentView.findViewById(R.id.recorder_ring_active_1);
        activeRecordingSecond = fragmentView.findViewById(R.id.recorder_ring_active_2);
        startRecording = fragmentView.findViewById(R.id.start_recording_button);
        stopRecording = fragmentView.findViewById(R.id.stop_recording_button);

        recordingGuidance = fragmentView.findViewById(R.id.guidance_text);
//        recordingTimer = fragmentView.findViewById(R.id.timer_text);
        chronometer = fragmentView.findViewById(R.id.chronometer);
        timerFormat = chronometer.getFormat();
        recordingTimer = fragmentView.findViewById(R.id.recording_timer_total);

        setListeners();
    }

    private void setListeners() {
        startRecording.setOnClickListener(view -> startRecordingSimulation());
        stopRecording.setOnClickListener(view -> stopRecordingSimulation());

        chronometer.setOnChronometerTickListener(chronometer -> {
            changeAudioVisuals();
        });

        recordingTimer.setOnCloseIconClickListener(view -> clearChronometerInfo());
    }

    private void startRecordingSimulation() {
        startRecording.setVisibility(View.INVISIBLE);
        stopRecording.setVisibility(View.VISIBLE);

        inactiveRecording.setVisibility(View.INVISIBLE);
        activeRecordingFirst.setVisibility(View.VISIBLE);

        changeText(true);
        startTimer();
    }

    private void stopRecordingSimulation() {
        startRecording.setVisibility(View.VISIBLE);
        stopRecording.setVisibility(View.INVISIBLE);

        inactiveRecording.setVisibility(View.VISIBLE);
        activeRecordingFirst.setVisibility(View.INVISIBLE);
        activeRecordingSecond.setVisibility(View.INVISIBLE);

        changeText(false);
        stopTimer();
    }

    private void changeAudioVisuals() {
        if (activeRecordingFirst.getVisibility() == View.VISIBLE) {
            activeRecordingFirst.setVisibility(View.INVISIBLE);
            activeRecordingSecond.setVisibility(View.VISIBLE);
        } else {
            activeRecordingFirst.setVisibility(View.VISIBLE);
            activeRecordingSecond.setVisibility(View.INVISIBLE);
        }
    }

    private void changeText(boolean recording) {
        if (recording) {
            recordingGuidance.setText(Objects.requireNonNull(getContext()).getResources().getString(R.string.tap_to_stop));
        } else {
            recordingGuidance.setText(Objects.requireNonNull(getContext()).getResources().getString(R.string.tap_to_record));
        }
    }

    private void startTimer() {
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
    }

    private void stopTimer() {
        chronometer.stop();
        timerValue = SystemClock.elapsedRealtime() - chronometer.getBase();
        chronometer.setVisibility(View.GONE);

        showChip();
    }

    private void showChip() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        recordingTimer.setText(simpleDateFormat.format(timerValue));
        recordingTimer.setVisibility(View.VISIBLE);
    }

    private void clearChronometerInfo() {
        timerValue = 0L;
        chronometer.setBase(SystemClock.elapsedRealtime());
        recordingTimer.setVisibility(View.GONE);
        chronometer.setVisibility(View.VISIBLE);
    }

}
