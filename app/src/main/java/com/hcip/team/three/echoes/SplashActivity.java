package com.hcip.team.three.echoes;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hcip.team.three.echoes.activities.MainActivity;
import com.hcip.team.three.echoes.model.Echo;
import com.hcip.team.three.echoes.model.Friend;
import com.hcip.team.three.echoes.model.Mood;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {

    private EchoesApplication echoesApplication;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        echoesApplication = (EchoesApplication) getApplication();
        splashScreen();
    }

    private void splashScreen() {
        // Show splash screen for 2 seconds
        // before launching the app
        int SPLASH_DISPLAY_LENGTH = 2000;
        new Handler().postDelayed(() -> {
            try {
                generateEchoes();
                generateFriends();
                generateMoods();

                goToEchoesScreen();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private void generateEchoes() {
        ArrayList<Echo> echoes = new ArrayList<>();
        Echo echo1 = new Echo(0, 1, echoesApplication.dateFromString("17 Jul 2020"));
        echo1.addImage(echoesApplication.imageEncoder(R.drawable.img_echo1));
        echo1.setMood(2);
        echo1.setTitle("Fun times with my friends");
        echoes.add(echo1);

        Echo echo2 = new Echo(1, 1, echoesApplication.dateFromString("07 Mar 2021"));
        echo2.addImage(echoesApplication.imageEncoder(R.drawable.img_echo2));
        echo2.setMood(4);
        echo2.setTitle("Tbt times with no covid :(");
        echoes.add(echo2);

        // Not created by Joanna
        Echo echo3 = new Echo(2, 6, echoesApplication.dateFromString("11 Apr 2021"));
        echo3.addImage(echoesApplication.imageEncoder(R.drawable.img_echo3));
        echo3.setMood(1);
        echo3.setTitle("First time in my new workplace!");
        echoes.add(echo3);

        echoesApplication.setEchoes(echoes);
    }

    private void generateFriends() {
        ArrayList<Friend> friends = new ArrayList<>();
        friends.add(new Friend(0, "Joanna Mills", echoesApplication.imageEncoder(R.drawable.img_profile_you), true));
        friends.add(new Friend(1, "Caroline Black", echoesApplication.imageEncoder(R.drawable.img_profile_caroline), false));
        friends.add(new Friend(2, "Amy Adams", echoesApplication.imageEncoder(R.drawable.img_profile_amy), false));
        friends.add(new Friend(3, "Frank Benson", echoesApplication.imageEncoder(R.drawable.img_profile_frank), false));
        friends.add(new Friend(4, "Mike Geller", echoesApplication.imageEncoder(R.drawable.img_profile_mike), false));
        friends.add(new Friend(5, "Billy Jackson", echoesApplication.imageEncoder(R.drawable.img_profile_billy), false));
        friends.add(new Friend(6, "Mark Jobs", echoesApplication.imageEncoder(R.drawable.img_profile_mark), false));


        echoesApplication.setFriends(friends);
    }

    private void generateMoods() {
        ArrayList<Mood> moods = new ArrayList<>();
        moods.add(new Mood(0, echoesApplication.imageEncoder(R.drawable.ic_mood_happy), "Happy"));
        moods.add(new Mood(1, echoesApplication.imageEncoder(R.drawable.ic_mood_excited), "Excited"));
        moods.add(new Mood(2, echoesApplication.imageEncoder(R.drawable.ic_mood_fascinated), "Fascinated"));
        moods.add(new Mood(3, echoesApplication.imageEncoder(R.drawable.ic_mood_in_love), "In love"));
        moods.add(new Mood(4, echoesApplication.imageEncoder(R.drawable.ic_mood_sad), "Sad"));
        moods.add(new Mood(5, echoesApplication.imageEncoder(R.drawable.ic_mood_devastated), "Devastated"));
        moods.add(new Mood(6, echoesApplication.imageEncoder(R.drawable.ic_mood_oh_no), "Oh no!"));
        moods.add(new Mood(7, echoesApplication.imageEncoder(R.drawable.ic_mood_exhausted), "Exhausted"));

        echoesApplication.setMoods(moods);
    }

    private void goToEchoesScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
