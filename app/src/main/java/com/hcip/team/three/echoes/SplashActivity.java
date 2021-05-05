package com.hcip.team.three.echoes;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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
        Echo echo1 = new Echo(0, 1, echoesApplication.dateFromString("17/07/2020"));
        echo1.addImage(echoesApplication.imageEncoder(R.drawable.img_echo1, false));
        echo1.setMood(2);
        echo1.setTitle("Fun times with my friends");
        echo1.setLocation("Greece");
        echoes.add(echo1);

        Echo echo2 = new Echo(1, 9, echoesApplication.dateFromString("07/03/2021"));
        echo2.addImage(echoesApplication.imageEncoder(R.drawable.img_echo2, false));
        echo2.setMood(4);
        echo2.setTitle("Tbt times with no covid :(");
        echo2.setLocation("Urban 58, Madrid, Spain");
        echoes.add(echo2);

        // Not created by Joanna
        Echo echo3 = new Echo(2, 9, echoesApplication.dateFromString("11/04/2021"));
        echo3.addImage(echoesApplication.imageEncoder(R.drawable.img_echo3, false));
        echo3.setMood(1);
        echo3.setTitle("First time in my new workplace!");
        echo3.setLocation("Madrid, Spain");
        echoes.add(echo3);

        echoesApplication.setEchoes(echoes);
    }

    private void generateFriends() {
        ArrayList<Friend> friends = new ArrayList<>();
        friends.add(new Friend(0, "Caroline Black", echoesApplication.imageEncoder(R.drawable.img_profile_caroline, false), false));
        friends.add(new Friend(1, "Amy Adams", echoesApplication.imageEncoder(R.drawable.img_profile_amy, false), false));
        friends.add(new Friend(2, "Frank Benson", echoesApplication.imageEncoder(R.drawable.img_profile_frank, false), false));
        friends.add(new Friend(3, "Mike Geller", echoesApplication.imageEncoder(R.drawable.img_profile_mike, false), false));
        friends.add(new Friend(4, "Billy Jackson", echoesApplication.imageEncoder(R.drawable.img_profile_billy, false), false));
        friends.add(new Friend(5, "Mark Jobs", echoesApplication.imageEncoder(R.drawable.img_profile_mark, false), false));
        friends.add(new Friend(6, "Devon Lane", echoesApplication.imageEncoder(R.drawable.img_profile_devon, false), false));
        friends.add(new Friend(7, "Adam Nicholson", echoesApplication.imageEncoder(R.drawable.img_profile_adam, false), false));
        friends.add(new Friend(8, "Emma Jones", echoesApplication.imageEncoder(R.drawable.img_profile_emma, false), false));
        friends.add(new Friend(9, "Joanna Mills", echoesApplication.imageEncoder(R.drawable.img_profile_you, false), true));

        echoesApplication.setFriends(friends);
    }

    private void generateMoods() {
        ArrayList<Mood> moods = new ArrayList<>();
        moods.add(new Mood(0, echoesApplication.imageEncoder(R.drawable.ic_mood_happy, true), "Happy"));
        moods.add(new Mood(1, echoesApplication.imageEncoder(R.drawable.ic_mood_excited, true), "Excited"));
        moods.add(new Mood(2, echoesApplication.imageEncoder(R.drawable.ic_mood_fascinated, true), "Fascinated"));
        moods.add(new Mood(3, echoesApplication.imageEncoder(R.drawable.ic_mood_in_love, true), "In love"));
        moods.add(new Mood(4, echoesApplication.imageEncoder(R.drawable.ic_mood_sad, true), "Sad"));
        moods.add(new Mood(5, echoesApplication.imageEncoder(R.drawable.ic_mood_devastated, true), "Devastated"));
        moods.add(new Mood(6, echoesApplication.imageEncoder(R.drawable.ic_mood_oh_no, true), "Oh no!"));
        moods.add(new Mood(7, echoesApplication.imageEncoder(R.drawable.ic_mood_exhausted, true), "Exhausted"));

        echoesApplication.setMoods(moods);
    }

    private void goToEchoesScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
