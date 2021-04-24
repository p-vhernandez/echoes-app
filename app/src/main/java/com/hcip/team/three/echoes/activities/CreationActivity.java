package com.hcip.team.three.echoes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hcip.team.three.echoes.EchoesApplication;
import com.hcip.team.three.echoes.R;

public class CreationActivity extends AppCompatActivity {

    private EchoesApplication echoesApplication;

    private ImageView btnBack;
    private Button btnFinish;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation);

        echoesApplication = (EchoesApplication) getApplication();

        initialize();
    }

    private void initialize() {
        btnBack = findViewById(R.id.button_back);
        btnFinish = findViewById(R.id.button_finish_creation);

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
    }
}
