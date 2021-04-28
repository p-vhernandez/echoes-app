package com.hcip.team.three.echoes.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.hcip.team.three.echoes.EchoesApplication;
import com.hcip.team.three.echoes.R;
import com.hcip.team.three.echoes.fragments.creation.AudioFragment;
import com.hcip.team.three.echoes.fragments.creation.CameraFragment;
import com.hcip.team.three.echoes.fragments.creation.DateFragment;
import com.hcip.team.three.echoes.fragments.creation.LocationFragment;
import com.hcip.team.three.echoes.fragments.creation.MoodFragment;
import com.hcip.team.three.echoes.fragments.creation.MusicFragment;
import com.hcip.team.three.echoes.fragments.creation.TagsFragment;
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

    private ImageView imgCamera;
    private ImageView imgMood;
    private ImageView imgText;
    private ImageView imgAudio;
    private ImageView imgDate;
    private ImageView imgTags;
    private ImageView imgLocation;
    private ImageView imgMusic;

    private TextView txtCamera;
    private TextView txtMood;
    private TextView txtText;
    private TextView txtAudio;
    private TextView txtDate;
    private TextView txtTags;
    private TextView txtLocation;
    private TextView txtMusic;

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
        fragmentText = new TextFragment();
        fragmentAudio = new AudioFragment();
        fragmentDate = new DateFragment();
        fragmentTags = new TagsFragment();
        fragmentLocation = new LocationFragment();
        fragmentMusic = new MusicFragment();

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

        imgCamera = creationNavigation.findViewById(R.id.icon_camera);
        imgMood = creationNavigation.findViewById(R.id.icon_mood);
        imgText = creationNavigation.findViewById(R.id.icon_text);
        imgAudio = creationNavigation.findViewById(R.id.icon_audio);
        imgDate = creationNavigation.findViewById(R.id.icon_calendar);
        imgTags = creationNavigation.findViewById(R.id.icon_tags);
        imgLocation = creationNavigation.findViewById(R.id.icon_location);
        imgMusic = creationNavigation.findViewById(R.id.icon_music);

        txtCamera = creationNavigation.findViewById(R.id.text_camera);
        txtMood = creationNavigation.findViewById(R.id.text_mood);
        txtText = creationNavigation.findViewById(R.id.text_text);
        txtAudio = creationNavigation.findViewById(R.id.text_audio);
        txtDate = creationNavigation.findViewById(R.id.text_calendar);
        txtTags = creationNavigation.findViewById(R.id.text_tags);
        txtLocation = creationNavigation.findViewById(R.id.text_location);
        txtMusic = creationNavigation.findViewById(R.id.text_music);

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
        btnText.setOnClickListener(v -> {
            if (activeFragment != fragmentText) {
                unselectButton(selectedButton);
                selectButton(TXT_TEXT);

                fragmentManager.beginTransaction().hide(activeFragment).show(fragmentText).commit();
                activeFragment = fragmentText;
                selectedButton = TXT_TEXT;
            }
        });
        btnAudio.setOnClickListener(v -> {
            if (activeFragment != fragmentAudio) {
                unselectButton(selectedButton);
                selectButton(TXT_AUDIO);

                fragmentManager.beginTransaction().hide(activeFragment).show(fragmentAudio).commit();
                activeFragment = fragmentAudio;
                selectedButton = TXT_AUDIO;
            }
        });
        btnDate.setOnClickListener(v -> {
            if (activeFragment != fragmentDate) {
                unselectButton(selectedButton);
                selectButton(TXT_CALENDAR);

                fragmentManager.beginTransaction().hide(activeFragment).show(fragmentDate).commit();
                activeFragment = fragmentDate;
                selectedButton = TXT_CALENDAR;
            }
        });
        btnTags.setOnClickListener(v -> {
            if (activeFragment != fragmentTags) {
                unselectButton(selectedButton);
                selectButton(TXT_TAGS);

                fragmentManager.beginTransaction().hide(activeFragment).show(fragmentTags).commit();
                activeFragment = fragmentTags;
                selectedButton = TXT_TAGS;
            }
        });
        btnLocation.setOnClickListener(v -> {
            if (activeFragment != fragmentLocation) {
                unselectButton(selectedButton);
                selectButton(TXT_LOCATION);

                fragmentManager.beginTransaction().hide(activeFragment).show(fragmentLocation).commit();
                activeFragment = fragmentLocation;
                selectedButton = TXT_LOCATION;
            }
        });
        btnMusic.setOnClickListener(v -> {
            if (activeFragment != fragmentMusic) {
                unselectButton(selectedButton);
                selectButton(TXT_MUSIC);

                fragmentManager.beginTransaction().hide(activeFragment).show(fragmentMusic).commit();
                activeFragment = fragmentMusic;
                selectedButton = TXT_MUSIC;
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
            case TXT_TEXT:
                if (isActive) {
                    btnText.setBackground(getResources().getDrawable(R.drawable.drw_gray_circle));
                } else {
                    btnText.setBackground(getResources().getDrawable(R.drawable.drw_selected_circle));
                }

                break;
            case TXT_AUDIO:
                if (isActive) {
                    btnAudio.setBackground(getResources().getDrawable(R.drawable.drw_gray_circle));
                } else {
                    btnAudio.setBackground(getResources().getDrawable(R.drawable.drw_selected_circle));
                }

                break;
            case TXT_CALENDAR:
                if (isActive) {
                    btnDate.setBackground(getResources().getDrawable(R.drawable.drw_gray_circle));
                } else {
                    btnDate.setBackground(getResources().getDrawable(R.drawable.drw_selected_circle));
                }

                break;
            case TXT_TAGS:
                if (isActive) {
                    btnTags.setBackground(getResources().getDrawable(R.drawable.drw_gray_circle));
                } else {
                    btnTags.setBackground(getResources().getDrawable(R.drawable.drw_selected_circle));
                }

                break;
            case TXT_LOCATION:
                if (isActive) {
                    btnLocation.setBackground(getResources().getDrawable(R.drawable.drw_gray_circle));
                } else {
                    btnLocation.setBackground(getResources().getDrawable(R.drawable.drw_selected_circle));
                }

                break;
            case TXT_MUSIC:
                if (isActive) {
                    btnMusic.setBackground(getResources().getDrawable(R.drawable.drw_gray_circle));
                } else {
                    btnMusic.setBackground(getResources().getDrawable(R.drawable.drw_selected_circle));
                }

                break;
        }
    }

    private void changeItemColors(boolean isActive, String toChange) {
        switch (toChange) {
            case TXT_CAMERA:
                if (isActive) {
                    imgCamera.setColorFilter(getResources().getColor(R.color.label_gray));
                    txtCamera.setTextColor(getResources().getColor(R.color.label_gray));
                } else {
                    imgCamera.setColorFilter(getResources().getColor(R.color.label_active));
                    txtCamera.setTextColor(getResources().getColor(R.color.label_active));
                }

                break;
            case TXT_MOOD:
                if (isActive) {
                    imgMood.setColorFilter(getResources().getColor(R.color.label_gray));
                    txtMood.setTextColor(getResources().getColor(R.color.label_gray));
                } else {
                    imgMood.setColorFilter(getResources().getColor(R.color.label_active));
                    txtMood.setTextColor(getResources().getColor(R.color.label_active));
                }

                break;
            case TXT_TEXT:
                if (isActive) {
                    imgText.setColorFilter(getResources().getColor(R.color.label_gray));
                    txtText.setTextColor(getResources().getColor(R.color.label_gray));
                } else {
                    imgText.setColorFilter(getResources().getColor(R.color.label_active));
                    txtText.setTextColor(getResources().getColor(R.color.label_active));
                }

                break;
            case TXT_AUDIO:
                if (isActive) {
                    imgAudio.setColorFilter(getResources().getColor(R.color.label_gray));
                    txtAudio.setTextColor(getResources().getColor(R.color.label_gray));
                } else {
                    imgAudio.setColorFilter(getResources().getColor(R.color.label_active));
                    txtAudio.setTextColor(getResources().getColor(R.color.label_active));
                }

                break;
            case TXT_CALENDAR:
                if (isActive) {
                    imgDate.setColorFilter(getResources().getColor(R.color.label_gray));
                    txtDate.setTextColor(getResources().getColor(R.color.label_gray));
                } else {
                    imgDate.setColorFilter(getResources().getColor(R.color.label_active));
                    txtDate.setTextColor(getResources().getColor(R.color.label_active));
                }

                break;
            case TXT_TAGS:
                if (isActive) {
                    imgTags.setColorFilter(getResources().getColor(R.color.label_gray));
                    txtTags.setTextColor(getResources().getColor(R.color.label_gray));
                } else {
                    imgTags.setColorFilter(getResources().getColor(R.color.label_active));
                    txtTags.setTextColor(getResources().getColor(R.color.label_active));
                }

                break;
            case TXT_LOCATION:
                if (isActive) {
                    imgLocation.setColorFilter(getResources().getColor(R.color.label_gray));
                    txtLocation.setTextColor(getResources().getColor(R.color.label_gray));
                } else {
                    imgLocation.setColorFilter(getResources().getColor(R.color.label_active));
                    txtLocation.setTextColor(getResources().getColor(R.color.label_active));
                }

                break;
            case TXT_MUSIC:
                if (isActive) {
                    imgMusic.setColorFilter(getResources().getColor(R.color.label_gray));
                    txtMusic.setTextColor(getResources().getColor(R.color.label_gray));
                } else {
                    imgMusic.setColorFilter(getResources().getColor(R.color.label_active));
                    txtMusic.setTextColor(getResources().getColor(R.color.label_active));
                }

                break;
        }
    }

}
