package com.hcip.team.three.echoes.fragments.display;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.hcip.team.three.echoes.EchoesApplication;
import com.hcip.team.three.echoes.R;

import java.util.Objects;

public class TabsFragment extends Fragment {

    private EchoesApplication echoesApplication;

    private View fragmentView;

    private FragmentManager fragmentManager;
    private Fragment homeFragment;
    private Fragment calendarFragment;
    private Fragment mapFragment;
    private Fragment activeFragment;

    private LinearLayout btnList;
    private LinearLayout btnCalendar;
    private LinearLayout btnMap;

    private ImageView listIcon;
    private ImageView calendarIcon;
    private ImageView mapIcon;

    private TextView listText;
    private TextView calendarText;
    private TextView mapText;

    private static final String TXT_LIST = "list";
    private static final String TXT_CALENDAR = "calendar";
    private static final String TXT_MAP = "map";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_tabs, parent, false);
        echoesApplication = (EchoesApplication) Objects.requireNonNull(getActivity()).getApplication();

        fragmentManager = getChildFragmentManager();

        initializeFragmentManager();
        initialize();

        return fragmentView;
    }

    private void initializeFragmentManager() {
        homeFragment = new HomeFragment();
        calendarFragment = new CalendarFragment();
        mapFragment = new MapFragment();
        activeFragment = homeFragment;

        fragmentManager.beginTransaction().add(R.id.fragment_content, mapFragment, "3").hide(mapFragment).commit();
        fragmentManager.beginTransaction().add(R.id.fragment_content, calendarFragment, "2").hide(calendarFragment).commit();
        fragmentManager.beginTransaction().add(R.id.fragment_content, homeFragment, "1").commit();
    }

    private void initialize() {
        btnList = fragmentView.findViewById(R.id.button_list);
        btnCalendar = fragmentView.findViewById(R.id.button_calendar);
        btnMap = fragmentView.findViewById(R.id.button_map);

        listIcon = fragmentView.findViewById(R.id.list_item);
        calendarIcon = fragmentView.findViewById(R.id.calendar_item);
        mapIcon = fragmentView.findViewById(R.id.map_item);

        listText = fragmentView.findViewById(R.id.list_text);
        calendarText = fragmentView.findViewById(R.id.calendar_text);
        mapText = fragmentView.findViewById(R.id.map_text);

        setUpListeners();
    }

    private void setUpListeners() {
        btnList.setOnClickListener(view -> {
            changeSelectedVisuals(TXT_LIST);
            changeActiveFragment(TXT_LIST);
        });

        btnCalendar.setOnClickListener(view -> {
            changeSelectedVisuals(TXT_CALENDAR);
            changeActiveFragment(TXT_CALENDAR);
        });

        btnMap.setOnClickListener(view -> {
            changeSelectedVisuals(TXT_MAP);
            changeActiveFragment(TXT_MAP);
        });
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void changeSelectedVisuals(String selected) {
        switch (selected) {
            case TXT_LIST:
                if (activeFragment != homeFragment) {
                    btnList.setBackground(Objects.requireNonNull(getContext()).getResources().getDrawable(R.drawable.drw_tab_select));
                    btnCalendar.setBackground(null);
                    btnMap.setBackground(null);

                    listIcon.setColorFilter(getResources().getColor(R.color.white));
                    calendarIcon.setColorFilter(getResources().getColor(R.color.label_tab));
                    mapIcon.setColorFilter(getResources().getColor(R.color.label_tab));

                    listText.setTextColor(getResources().getColor(R.color.white));
                    calendarText.setTextColor(getResources().getColor(R.color.label_tab));
                    mapText.setTextColor(getResources().getColor(R.color.label_tab));
                }

                break;
            case TXT_CALENDAR:
                if (activeFragment != calendarFragment) {
                    btnList.setBackground(null);
                    btnCalendar.setBackground(Objects.requireNonNull(getContext()).getResources().getDrawable(R.drawable.drw_tab_select));
                    btnMap.setBackground(null);

                    listIcon.setColorFilter(getResources().getColor(R.color.label_tab));
                    calendarIcon.setColorFilter(getResources().getColor(R.color.white));
                    mapIcon.setColorFilter(getResources().getColor(R.color.label_tab));

                    listText.setTextColor(getResources().getColor(R.color.label_tab));
                    calendarText.setTextColor(getResources().getColor(R.color.white));
                    mapText.setTextColor(getResources().getColor(R.color.label_tab));
                }

                break;
            case TXT_MAP:
                if (activeFragment != mapFragment) {
                    btnList.setBackground(null);
                    btnCalendar.setBackground(null);
                    btnMap.setBackground(Objects.requireNonNull(getContext()).getResources().getDrawable(R.drawable.drw_tab_select));

                    listIcon.setColorFilter(getResources().getColor(R.color.label_tab));
                    calendarIcon.setColorFilter(getResources().getColor(R.color.label_tab));
                    mapIcon.setColorFilter(getResources().getColor(R.color.white));

                    listText.setTextColor(getResources().getColor(R.color.label_tab));
                    calendarText.setTextColor(getResources().getColor(R.color.label_tab));
                    mapText.setTextColor(getResources().getColor(R.color.white));
                }

                break;
        }
    }

    private void changeActiveFragment(String selected) {
        switch (selected) {
            case TXT_LIST:
                if (activeFragment != homeFragment) {
                    fragmentManager.beginTransaction().hide(activeFragment).show(homeFragment).commit();
                    activeFragment = homeFragment;
                }

                break;
            case TXT_CALENDAR:
                if (activeFragment != calendarFragment) {
                    fragmentManager.beginTransaction().hide(activeFragment).show(calendarFragment).commit();
                    activeFragment = calendarFragment;
                }

                break;
            case TXT_MAP:
                if (activeFragment != mapFragment) {
                    fragmentManager.beginTransaction().hide(activeFragment).show(mapFragment).commit();
                    activeFragment = mapFragment;
                }

                break;
        }
    }

}
