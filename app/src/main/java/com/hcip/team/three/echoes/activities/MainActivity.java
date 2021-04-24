package com.hcip.team.three.echoes.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hcip.team.three.echoes.EchoesApplication;
import com.hcip.team.three.echoes.R;
import com.hcip.team.three.echoes.fragments.DefaultNoContentFragment;
import com.hcip.team.three.echoes.fragments.display.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private EchoesApplication echoesApplication;

    private Fragment homeFragment;
    private Fragment searchFragment;
    private Fragment moodTrackerFragment;
    private Fragment profileFragment;
    private Fragment activeFragment;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        echoesApplication = (EchoesApplication) getApplication();
        fragmentManager = getSupportFragmentManager();

        initializeFragmentManager();
        createBottomNavigationBar();
        initialize();
    }

    private void initializeFragmentManager() {
        homeFragment = new HomeFragment();
        searchFragment = new DefaultNoContentFragment();
        moodTrackerFragment = new DefaultNoContentFragment();
        profileFragment = new DefaultNoContentFragment();
        activeFragment = homeFragment;

        fragmentManager.beginTransaction().add(R.id.fragment_content, profileFragment, "4").hide(profileFragment).commit();
        fragmentManager.beginTransaction().add(R.id.fragment_content, moodTrackerFragment, "3").hide(moodTrackerFragment).commit();
        fragmentManager.beginTransaction().add(R.id.fragment_content, searchFragment, "2").hide(searchFragment).commit();
        fragmentManager.beginTransaction().add(R.id.fragment_content, homeFragment, "1").commit();
    }

    @SuppressLint("NonConstantResourceId")
    private void createBottomNavigationBar() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.overview_page:
                    if (activeFragment != homeFragment) {
                        fragmentManager.beginTransaction().hide(activeFragment).show(homeFragment).commit();
                        activeFragment = homeFragment;
                    }

                    item.setChecked(true);
                    break;
                case R.id.search_page:
                    if (activeFragment != searchFragment) {
                        fragmentManager.beginTransaction().hide(activeFragment).show(searchFragment).commit();
                        activeFragment = searchFragment;
                    }

                    item.setChecked(true);
                    break;
                case R.id.mood_tracker_page:
                    if (activeFragment != moodTrackerFragment) {
                        fragmentManager.beginTransaction().hide(activeFragment).show(moodTrackerFragment).commit();
                        activeFragment = moodTrackerFragment;
                    }

                    item.setChecked(true);
                    break;
                case R.id.profile_page:
                    if (activeFragment != profileFragment) {
                        fragmentManager.beginTransaction().hide(activeFragment).show(profileFragment).commit();
                        activeFragment = profileFragment;
                    }

                    item.setChecked(true);
                    break;
            }

            return false;
        });
    }

    private void initialize() {

    }
}