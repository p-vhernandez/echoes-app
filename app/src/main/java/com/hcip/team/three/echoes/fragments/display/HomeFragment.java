package com.hcip.team.three.echoes.fragments.display;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hcip.team.three.echoes.EchoesApplication;
import com.hcip.team.three.echoes.R;
import com.hcip.team.three.echoes.activities.CreationActivity;
import com.hcip.team.three.echoes.activities.MainActivity;
import com.hcip.team.three.echoes.model.Echo;
import com.hcip.team.three.echoes.utils.EchoesAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class HomeFragment extends Fragment {

    private EchoesApplication echoesApplication;

    private View fragmentView;

    private ListView echoesList;
    private ArrayList<Echo> allEchoes;

    private Button btnCreateEcho;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_home, parent, false);
        echoesApplication = (EchoesApplication) Objects.requireNonNull(getActivity()).getApplication();

        initialize();
        setUpAdapter();

        return fragmentView;
    }

    private void initialize() {
        echoesList = fragmentView.findViewById(R.id.echoes_list);
        btnCreateEcho = fragmentView.findViewById(R.id.button_create_echo);

        allEchoes = echoesApplication.getEchoes();
        Collections.sort(allEchoes);
        Collections.reverse(allEchoes);

        setClickListeners();
    }

    private void setUpAdapter() {
        EchoesAdapter echoesAdapter = new EchoesAdapter(echoesApplication.getApplicationContext(), echoesApplication, allEchoes);
        echoesList.setAdapter(echoesAdapter);
    }

    private void setClickListeners() {
        btnCreateEcho.setOnClickListener(view -> {
            ((MainActivity) Objects.requireNonNull(getActivity())).goToEchoCreation();
        });
    }
}
