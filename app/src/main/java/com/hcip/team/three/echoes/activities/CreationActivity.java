package com.hcip.team.three.echoes.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hcip.team.three.echoes.EchoesApplication;
import com.hcip.team.three.echoes.R;

public class CreationActivity extends AppCompatActivity {

    private EchoesApplication echoesApplication;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        echoesApplication = (EchoesApplication) getApplication();

        initialize();
    }

    private void initialize() {

    }
}
