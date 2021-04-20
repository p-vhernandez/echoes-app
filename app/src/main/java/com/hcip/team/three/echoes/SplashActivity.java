package com.hcip.team.three.echoes;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

        // Hide navigation bar
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

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
        //TODO
        ArrayList<Echo> echoes = new ArrayList<>();
        echoesApplication.setEchoes(echoes);
    }

    private void generateFriends() {
        ArrayList<Friend> friends = new ArrayList<>();
        friends.add(new Friend(1, "Joanna Mills", echoesApplication.imageEncoder(R.drawable.you)));
        friends.add(new Friend(2, "Caroline Black", echoesApplication.imageEncoder(R.drawable.caroline)));
        friends.add(new Friend(3, "Amy Adams", echoesApplication.imageEncoder(R.drawable.amy)));
        friends.add(new Friend(4, "Frank Benson", echoesApplication.imageEncoder(R.drawable.frank)));
        friends.add(new Friend(4, "Mike Geller", echoesApplication.imageEncoder(R.drawable.mike)));
        friends.add(new Friend(4, "Billy Jackson", echoesApplication.imageEncoder(R.drawable.billy)));
        friends.add(new Friend(4, "Mark Jobs", echoesApplication.imageEncoder(R.drawable.mark)));


        echoesApplication.setFriends(friends);
    }

    private void generateMoods() {
        //TODO
        ArrayList<Mood> moods = new ArrayList<>();
        echoesApplication.setMoods(moods);
    }

    private void goToEchoesScreen() {
        Intent intent = new Intent(this, EchoesActivity.class);
        startActivity(intent);
        finish();
    }

}
