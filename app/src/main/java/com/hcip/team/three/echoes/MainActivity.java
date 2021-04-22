package com.hcip.team.three.echoes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private EchoesApplication echoesApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        echoesApplication = (EchoesApplication) getApplication();

        createBottomNavigationBar();
        initialize();
    }

    @SuppressLint("NonConstantResourceId")
    private void createBottomNavigationBar() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemReselectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.overview_page:
                    Toast.makeText(MainActivity.this, "Overview", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.search_page:
                    Toast.makeText(MainActivity.this, "Search", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.mood_tracker_page:
                    Toast.makeText(MainActivity.this, "Mood tracker", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.profile_page:
                    Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
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