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
        // TODO: add whatever attributes we feel like adding - discuss
        ArrayList<Echo> echoes = new ArrayList<>();
        Echo echo1 = new Echo(1, 1, "Happy holiday times!", echoesApplication.dateFromString("17 Jul 2020"));
        echo1.addImage(echoesApplication.imageEncoder(R.drawable.echo1));
        echoes.add(echo1);

        Echo echo2 = new Echo(2, 1, "Fun times with my friends", echoesApplication.dateFromString("07 Mar 2021"));
        echo1.addImage(echoesApplication.imageEncoder(R.drawable.echo2));
        echoes.add(echo2);

        // Not created by Joanna
        Echo echo3 = new Echo(3, 6, "First day at my new workplace!", echoesApplication.dateFromString("11 Apr 2021"));
        echo1.addImage(echoesApplication.imageEncoder(R.drawable.echo3));
        echoes.add(echo3);

        echoesApplication.setEchoes(echoes);
    }

    private void generateFriends() {
        ArrayList<Friend> friends = new ArrayList<>();
        friends.add(new Friend(1, "Joanna Mills", echoesApplication.imageEncoder(R.drawable.you), true));
        friends.add(new Friend(2, "Caroline Black", echoesApplication.imageEncoder(R.drawable.caroline), false));
        friends.add(new Friend(3, "Amy Adams", echoesApplication.imageEncoder(R.drawable.amy), false));
        friends.add(new Friend(4, "Frank Benson", echoesApplication.imageEncoder(R.drawable.frank), false));
        friends.add(new Friend(5, "Mike Geller", echoesApplication.imageEncoder(R.drawable.mike), false));
        friends.add(new Friend(6, "Billy Jackson", echoesApplication.imageEncoder(R.drawable.billy), false));
        friends.add(new Friend(7, "Mark Jobs", echoesApplication.imageEncoder(R.drawable.mark), false));


        echoesApplication.setFriends(friends);
    }

    private void generateMoods() {
        //TODO: which moods will be available? - discuss
        ArrayList<Mood> moods = new ArrayList<>();
        moods.add(new Mood(1, "", "Happy"));
        moods.add(new Mood(2, "", "Sad"));
        moods.add(new Mood(3, "", "Excited"));
        moods.add(new Mood(4, "", "Angry"));

        echoesApplication.setMoods(moods);
    }

    private void goToEchoesScreen() {
        Intent intent = new Intent(this, EchoesActivity.class);
        startActivity(intent);
        finish();
    }

}
