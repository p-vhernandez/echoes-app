package com.hcip.team.three.echoes.fragments.creation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hcip.team.three.echoes.EchoesApplication;
import com.hcip.team.three.echoes.R;
import com.hcip.team.three.echoes.utils.adapters.MoodsAdapter;

import java.util.Objects;

public class MoodFragment extends Fragment {

    private EchoesApplication echoesApplication;

    private View fragmentView;

    private GridView gridView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_creation_mood, parent, false);
        echoesApplication = (EchoesApplication) Objects.requireNonNull(getActivity()).getApplication();

        initialize();

        return fragmentView;
    }

    private void initialize() {
        gridView = fragmentView.findViewById(R.id.mood_grid);
        setUpAdapter();
    }

    private void setUpAdapter() {
        MoodsAdapter adapter = new MoodsAdapter(requireContext(), echoesApplication);
        gridView.setAdapter(adapter);
    }

}
