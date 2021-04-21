package com.hcip.team.three.echoes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.hcip.team.three.echoes.model.Echo;
import com.hcip.team.three.echoes.model.Friend;

public class EchoesActivity extends AppCompatActivity {

    private EchoesApplication echoesApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_echoes);

        echoesApplication = (EchoesApplication) getApplication();

        initialize();
    }

    private void initialize() {
//        for (Friend friend : echoesApplication.getFriends()) {
//            Log.e("friend:", friend.getName());
//        }
//
//        for (Echo echo : echoesApplication.getEchoes()) {
//            Log.e("echo:", echo.getTitle());
//        }
    }
}