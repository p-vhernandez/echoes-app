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
    private ImageView echoFirstImage;
    private ImageView echoSecondImage;
    private ImageView echoThirdImage;
    private ImageView moodImage;
    private ImageView audioImage;

    private TextView echoTitle;
    private TextView echoDate;
    private TextView echoLocation;
    private TextView echoDescription;

    private ChipGroup tagsGroup;

    private Button btnAddToWall;

    private CheckBox checkBoxPrivacy;

    private Echo toOverview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        echoesApplication = (EchoesApplication) getApplication();
        toOverview = echoesApplication.getNewEcho();

        if (toOverview.getB64Pictures().size() == 1) {
            setContentView(R.layout.activity_echo_overview_single_image);
        } else {
            setContentView(R.layout.activity_echo_overview_three_images);
        }

        initialize();
    }

    private void initialize() {
        btnBack = findViewById(R.id.button_back);
        creatorImage = findViewById(R.id.creator_image);
        moodImage = findViewById(R.id.mood_image);
        audioImage = findViewById(R.id.audio_image);

        if (toOverview.getB64Pictures().size() == 1) {
            echoSingleImage = findViewById(R.id.echo_single_image);
        } else {
            echoFirstImage = findViewById(R.id.single_image_1);
            echoSecondImage = findViewById(R.id.single_image_2);
            echoThirdImage = findViewById(R.id.single_image_3);
        }

        echoTitle = findViewById(R.id.echo_title);
        echoDate = findViewById(R.id.echo_date);
        echoLocation = findViewById(R.id.echo_location);
        echoDescription = findViewById(R.id.echo_description);

        tagsGroup = findViewById(R.id.friends_chip);
        checkBoxPrivacy = findViewById(R.id.checkBox_privacy);
        btnAddToWall = findViewById(R.id.button_create_echo);

        setUpInformation();
        setUpListeners();
    }

    private void setUpInformation() {
        creatorImage.setImageDrawable(echoesApplication.imageDecoder(echoesApplication.getUser().getB64ProfilePicture()));

        if (toOverview.getB64Pictures().size() == 1) {
            echoSingleImage.setImageDrawable(echoesApplication.imageDecoder(toOverview.getB64Pictures().get(0)));
        } else {
            echoFirstImage.setImageDrawable(echoesApplication.imageDecoder(toOverview.getB64Pictures().get(0)));
            echoSecondImage.setImageDrawable(echoesApplication.imageDecoder(toOverview.getB64Pictures().get(1)));
            echoThirdImage.setImageDrawable(echoesApplication.imageDecoder(toOverview.getB64Pictures().get(2)));
        }

        if (toOverview.isHasMood()) {
            moodImage.setImageDrawable(echoesApplication.imageDecoder(echoesApplication.getMoods().get(toOverview.getMood()).getMoodImage()));
        } else {
            moodImage.setVisibility(View.INVISIBLE);
        }

        if (!toOverview.isHasAudio()) {
            audioImage.setVisibility(View.INVISIBLE);
        }

        echoTitle.setText(toOverview.getTitle());
        echoDate.setText(echoesApplication.stringFromDate(toOverview.getDate()));
        echoLocation.setText(toOverview.getLocation());

        if (toOverview.getTags() != null && toOverview.getTags().size() > 0) {
            for (Friend friend : toOverview.getTags()) {
                Chip friendChip = (Chip) getLayoutInflater().inflate(R.layout.custom_layout_chip_simple, tagsGroup, false);
                friendChip.setText(friend.getName());
                tagsGroup.addView(friendChip);
            }
        }

        if (toOverview.getDescription() != null && !toOverview.getDescription().equals("")) {
            echoDescription.setText(toOverview.getDescription());
        } else {
            echoDescription.setVisibility(View.GONE);
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
