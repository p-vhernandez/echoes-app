package com.hcip.team.three.echoes.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hcip.team.three.echoes.EchoesApplication;
import com.hcip.team.three.echoes.R;
import com.hcip.team.three.echoes.fragments.DefaultNoContentFragment;
import com.hcip.team.three.echoes.fragments.display.HomeFragment;
import com.hcip.team.three.echoes.fragments.display.MoodTrackerFragment;
import com.hcip.team.three.echoes.fragments.display.TabsFragment;

public class MainActivity extends AppCompatActivity {

    private EchoesApplication echoesApplication;

    private Fragment tabsFragment;
    private Fragment searchFragment;
    private Fragment moodTrackerFragment;
    private Fragment profileFragment;
    private Fragment activeFragment;

    private FragmentManager fragmentManager;

    private Toolbar toolbar;
    private TextView toolbarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        echoesApplication = (EchoesApplication) getApplication();
        fragmentManager = getSupportFragmentManager();

        initialize();
        initializeFragmentManager();
        createBottomNavigationBar();
    }

    private void initialize() {
        toolbar = findViewById(R.id.app_toolbar);
        toolbarText = toolbar.findViewById(R.id.toolbar_text);
    }

    private void initializeFragmentManager() {
        tabsFragment = new TabsFragment();
        searchFragment = new DefaultNoContentFragment();
        moodTrackerFragment = new MoodTrackerFragment();
        profileFragment = new DefaultNoContentFragment();
        activeFragment = tabsFragment;

        fragmentManager.beginTransaction().add(R.id.fragment_content, profileFragment, "4").hide(profileFragment).commit();
        fragmentManager.beginTransaction().add(R.id.fragment_content, moodTrackerFragment, "3").hide(moodTrackerFragment).commit();
        fragmentManager.beginTransaction().add(R.id.fragment_content, searchFragment, "2").hide(searchFragment).commit();
        fragmentManager.beginTransaction().add(R.id.fragment_content, tabsFragment, "1").commit();
    }

    @SuppressLint("NonConstantResourceId")
    private void createBottomNavigationBar() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.overview_page:
                    if (activeFragment != tabsFragment) {
                        fragmentManager.beginTransaction().hide(activeFragment).show(tabsFragment).commit();
                        activeFragment = tabsFragment;
                    }

                    showToolbar();
                    changeToolbarText(getString(R.string.app_name));
                    item.setChecked(true);
                    break;
                case R.id.search_page:
                    if (activeFragment != searchFragment) {
                        fragmentManager.beginTransaction().hide(activeFragment).show(searchFragment).commit();
                        activeFragment = searchFragment;
                    }

                    hideToolbar();
                    item.setChecked(true);
                    break;
                case R.id.mood_tracker_page:
                    if (activeFragment != moodTrackerFragment) {
                        fragmentManager.beginTransaction().hide(activeFragment).show(moodTrackerFragment).commit();
                        activeFragment = moodTrackerFragment;
                    }

                    showToolbar();
                    changeToolbarText(getString(R.string.mood_tracker_section));
                    item.setChecked(true);
                    break;
                case R.id.profile_page:
                    if (activeFragment != profileFragment) {
                        fragmentManager.beginTransaction().hide(activeFragment).show(profileFragment).commit();
                        activeFragment = profileFragment;
                    }

                    hideToolbar();
                    item.setChecked(true);
                    break;
            }

            return false;
        });
    }

    private void showToolbar() {
        this.toolbar.setVisibility(View.VISIBLE);
    }

    private void hideToolbar() {
        this.toolbar.setVisibility(View.GONE);
    }

    private void changeToolbarText(String toolbarText) {
        this.toolbarText.setText(toolbarText);
    }

    public void goToEchoCreation() {
        Intent intent = new Intent(this, CreationActivity.class);
        startActivity(intent);
//        finish();
    }
}