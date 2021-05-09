package com.hcip.team.three.echoes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.hcip.team.three.echoes.EchoesApplication;
import com.hcip.team.three.echoes.R;
import com.hcip.team.three.echoes.model.Echo;
import com.hcip.team.three.echoes.model.Friend;

public class EchoOverview extends AppCompatActivity {

    private EchoesApplication echoesApplication;

    private ImageView btnBack;
    private ImageView creatorImage;
    private ImageView echoSingleImage;
    private ImageView moodImage;
    private ImageView audioImage;

    private TextView echoTitle;
    private TextView echoDate;
    private TextView echoLocation;

    private ChipGroup tagsGroup;

    private Button btnAddToWall;

    private CheckBox checkBoxPrivacy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_echo_overview_single_image);
        echoesApplication = (EchoesApplication) getApplication();

        initialize();
    }

    private void initialize() {
        btnBack = findViewById(R.id.button_back);
        creatorImage = findViewById(R.id.creator_image);
        echoSingleImage = findViewById(R.id.echo_single_image);
        moodImage = findViewById(R.id.mood_image);
        audioImage = findViewById(R.id.audio_image);

        echoTitle = findViewById(R.id.echo_title);
        echoDate = findViewById(R.id.echo_date);
        echoLocation = findViewById(R.id.echo_location);

        tagsGroup = findViewById(R.id.friends_chip);
        checkBoxPrivacy = findViewById(R.id.checkBox_privacy);
        btnAddToWall = findViewById(R.id.button_create_echo);

        setUpInformation();
        setUpListeners();
    }

    private void setUpInformation() {
        Echo echo = echoesApplication.getNewEcho();
        creatorImage.setImageDrawable(echoesApplication.imageDecoder(echoesApplication.getUser().getB64ProfilePicture()));
        echoSingleImage.setImageDrawable(echoesApplication.imageDecoder(echo.getB64Pictures().get(0)));

        if (echo.isHasMood()) {
            moodImage.setImageDrawable(echoesApplication.imageDecoder(echoesApplication.getMoods().get(echo.getMood()).getMoodImage()));
        } else {
            moodImage.setVisibility(View.INVISIBLE);
        }

        if (!echo.isHasAudio()) {
            audioImage.setVisibility(View.INVISIBLE);
        }

        echoTitle.setText(echo.getTitle());
        echoDate.setText(echoesApplication.stringFromDate(echo.getDate()));
        echoLocation.setText(echo.getLocation());

        if (echo.getTags() != null && echo.getTags().size() > 0) {
            for (Friend friend : echo.getTags()) {
                Chip friendChip = (Chip) getLayoutInflater().inflate(R.layout.custom_layout_chip_simple, tagsGroup, false);
                friendChip.setText(friend.getName());
                tagsGroup.addView(friendChip);
            }
        }
    }

    private void setUpListeners() {
        checkBoxPrivacy.setOnClickListener(v -> changePrivacy());
        btnAddToWall.setOnClickListener(v -> createEcho());
        btnBack.setOnClickListener(v -> goBackToCreation());
    }

    private void changePrivacy() {
        echoesApplication.getNewEcho().changePrivacy();
    }

    private void createEcho() {
        echoesApplication.getEchoes().add(echoesApplication.getNewEcho());
        echoesApplication.finishEchoCreation();

        goBackToMainActivity();
    }

    private void goBackToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void goBackToCreation() {
        finish();
    }

}
