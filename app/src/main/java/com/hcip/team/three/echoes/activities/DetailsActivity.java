package com.hcip.team.three.echoes.activities;

import android.annotation.SuppressLint;
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

public class DetailsActivity extends AppCompatActivity {

    private EchoesApplication echoesApplication;

    private ImageView btnBack;
    private ImageView creatorImage;
    private ImageView echoSingleImage;
    private ImageView echoFirstImage;
    private ImageView echoSecondImage;
    private ImageView echoThirdImage;
    private ImageView moodImage;
    private ImageView audioImage;
    private ImageView pinImage;

    private TextView echoTitle;
    private TextView echoDate;
    private TextView echoLocation;
    private TextView echoDescription;

    private ChipGroup tagsGroup;

    private Echo toOverview;

    private boolean pinned;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_echo_details);

        echoesApplication = (EchoesApplication) getApplication();

        Intent intent = getIntent();
        String type = intent.getStringExtra("echo");

        if (type.equals("saddest")) {
            toOverview = echoesApplication.getSaddestEcho();
        } else {
            toOverview = echoesApplication.getHappiestEcho();
        }

        initialize();
    }

    private void initialize() {
        btnBack = findViewById(R.id.button_back);
        creatorImage = findViewById(R.id.creator_image);
        moodImage = findViewById(R.id.mood_image);
        audioImage = findViewById(R.id.audio_image);
        pinImage = findViewById(R.id.pin_image);

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

        pinned = false;
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
        btnBack.setOnClickListener(v -> goBackToMoodTracker());
        pinImage.setOnClickListener(view -> pinEchoToTheWall());
    }

    private void goBackToMoodTracker() {
        finish();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void pinEchoToTheWall() {
        if (pinned) {
            pinImage.setBackground(null);
            pinImage.clearColorFilter();
        } else {
            pinImage.setBackground(getResources().getDrawable(R.drawable.drw_ic_echo_audio_background));
            pinImage.setColorFilter(getResources().getColor(R.color.white));
        }

        pinned = !pinned;
    }

}
