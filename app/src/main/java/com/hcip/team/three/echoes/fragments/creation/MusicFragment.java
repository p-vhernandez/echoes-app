package com.hcip.team.three.echoes.fragments.creation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hcip.team.three.echoes.EchoesApplication;
import com.hcip.team.three.echoes.R;

import java.util.Objects;

public class MusicFragment extends Fragment {

    private EchoesApplication echoesApplication;

    private View fragmentView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_not_allowed, parent, false);
        echoesApplication = (EchoesApplication) Objects.requireNonNull(getActivity()).getApplication();

        return fragmentView;
    }

}
