package com.hcip.team.three.echoes.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.hcip.team.three.echoes.EchoesApplication;
import com.hcip.team.three.echoes.R;
import com.hcip.team.three.echoes.fragments.creation.CameraFragment;
import com.hcip.team.three.echoes.fragments.creation.MoodFragment;
import com.hcip.team.three.echoes.fragments.creation.TextFragment;

public class CreationActivity extends AppCompatActivity {

    private EchoesApplication echoesApplication;

    private ImageView btnBack;
    private Button btnFinish;

    private ConstraintLayout creationNavigation;

    private RelativeLayout btnCamera;
    private RelativeLayout btnMood;
    private RelativeLayout btnText;
    private RelativeLayout btnAudio;
    private RelativeLayout btnDate;
    private RelativeLayout btnTags;
    private RelativeLayout btnLocation;
    private RelativeLayout btnMusic;

    private Fragment fragmentCamera;
    private Fragment fragmentMood;
    private Fragment fragmentText;
    private Fragment fragmentAudio;
    private Fragment fragmentDate;
    private Fragment fragmentTags;
    private Fragment fragmentLocation;
    private Fragment fragmentMusic;
    private Fragment activeFragment;

    private FragmentManager fragmentManager;

    private String selectedButton;

    private static final String TXT_CAMERA = "camera";
    private static final String TXT_MOOD = "mood";
    private static final String TXT_TEXT = "text";
    private static final String TXT_AUDIO = "audio";
    private static final String TXT_CALENDAR = "calendar";
    private static final String TXT_TAGS = "tags";
    private static final String TXT_LOCATION = "location";
    private static final String TXT_MUSIC = "music";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation);

        echoesApplication = (EchoesApplication) getApplication();
        fragmentManager = getSupportFragmentManager();

        initializeFragmentManager();
        initialize();
    }

    private void initializeFragmentManager() {
        fragmentCamera = new CameraFragment();
        fragmentMood = new MoodFragment();

        activeFragment = fragmentCamera;
        selectedButton = TXT_CAMERA;

        fragmentManager.beginTransaction().add(R.id.fragment_content, fragmentMood, "2").hide(fragmentMood).commit();
        fragmentManager.beginTransaction().add(R.id.fragment_content, fragmentCamera, "1").commit();
    }

    private void initialize() {
        btnBack = findViewById(R.id.button_back);
        btnFinish = findViewById(R.id.button_finish_creation);
        creationNavigation = findViewById(R.id.creation_navigation);

        btnCamera = creationNavigation.findViewById(R.id.btn_camera);
        btnMood = creationNavigation.findViewById(R.id.btn_mood);
        btnText = creationNavigation.findViewById(R.id.btn_text);
        btnAudio = creationNavigation.findViewById(R.id.btn_audio);
        btnDate = creationNavigation.findViewById(R.id.btn_calendar);
        btnTags = creationNavigation.findViewById(R.id.btn_tags);
        btnLocation = creationNavigation.findViewById(R.id.btn_location);
        btnMusic = creationNavigation.findViewById(R.id.btn_music);


        selectButton(TXT_CAMERA);
        setClickListeners();
    }

    private void setClickListeners() {
        btnBack.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        btnFinish.setOnClickListener(view -> {
            // TODO
        });

        setUpFragmentTransitions();
    }

    private void setUpFragmentTransitions() {
        btnCamera.setOnClickListener(v -> {
            if (activeFragment != fragmentCamera) {
                unselectButton(selectedButton);
                selectButton(TXT_CAMERA);

                fragmentManager.beginTransaction().hide(activeFragment).show(fragmentCamera).commit();
                activeFragment = fragmentCamera;
                selectedButton = TXT_CAMERA;
            }
        });

        btnMood.setOnClickListener(v -> {
            if (activeFragment != fragmentMood) {
                unselectButton(selectedButton);
                selectButton(TXT_MOOD);

                fragmentManager.beginTransaction().hide(activeFragment).show(fragmentMood).commit();
                activeFragment = fragmentMood;
                selectedButton = TXT_MOOD;
            }
        });
    }

    private void unselectButton(String selected) {
        changeButtonBackground(true, selected);
        changeItemColors(true, selected);
    }

    private void selectButton(String toSelect) {
        changeButtonBackground(false, toSelect);
        changeItemColors(false, toSelect);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void changeButtonBackground(boolean isActive, String toChange) {
        switch (toChange) {
            case TXT_CAMERA:
                if (isActive) {
                    btnCamera.setBackground(getResources().getDrawable(R.drawable.drw_gray_circle));
                } else {
                    btnCamera.setBackground(getResources().getDrawable(R.drawable.drw_selected_circle));
                }

                break;
            case TXT_MOOD:
                if (isActive) {
                    btnMood.setBackground(getResources().getDrawable(R.drawable.drw_gray_circle));
                } else {
                    btnMood.setBackground(getResources().getDrawable(R.drawable.drw_selected_circle));
                }

                break;
        }
    }

    private void changeItemColors(boolean isActive, String toChange) {
        switch (toChange) {
            case TXT_CAMERA:
                if (isActive) {

                } else {

                }

                break;
            case TXT_MOOD:
                if (isActive) {

                } else {

                }

                break;
        }
    }

}
