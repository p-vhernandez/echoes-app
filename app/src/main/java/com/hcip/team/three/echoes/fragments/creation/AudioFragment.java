package com.hcip.team.three.echoes.fragments.creation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hcip.team.three.echoes.EchoesApplication;
import com.hcip.team.three.echoes.R;

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

        setListeners();
    }

    private void setListeners() {
        startRecording.setOnClickListener(view -> startRecordingSimulation());
        stopRecording.setOnClickListener(view -> stopRecordingSimulation());

        chronometer.setOnChronometerTickListener(chronometer -> {
            changeAudioVisuals();
        });
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
        chronometer.start();
    }

    private void stopTimer() {
        chronometer.stop();
    }

}
