package com.hcip.team.three.echoes.fragments.creation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.hcip.team.three.echoes.EchoesApplication;
import com.hcip.team.three.echoes.R;
import com.hcip.team.three.echoes.model.Friend;
import com.hcip.team.three.echoes.utils.adapters.FriendsAdapter;
import com.hcip.team.three.echoes.utils.interfaces.FriendSelectedListener;

import java.util.Objects;

public class TagsFragment extends Fragment implements FriendSelectedListener {

    private EchoesApplication echoesApplication;

    private View fragmentView;
    private ListView friendsList;

    private FriendsAdapter friendsAdapter;
    private ChipGroup chipGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_creation_tags, parent, false);
        echoesApplication = (EchoesApplication) Objects.requireNonNull(getActivity()).getApplication();

        initialize();

        return fragmentView;
    }

    private void initialize() {
        friendsList = fragmentView.findViewById(R.id.friends_list);
        chipGroup = fragmentView.findViewById(R.id.friends_chip);

        setUpAdapter();
    }

    private void setUpAdapter() {
        friendsAdapter = new FriendsAdapter(requireContext(), echoesApplication, this);
        friendsList.setAdapter(friendsAdapter);
    }

    private void createChip(Friend friend) {
        Chip friendChip = (Chip) getLayoutInflater().inflate(R.layout.custom_layout_chip, chipGroup, false);
        friendChip.setText(friend.getName());

        friendChip.setOnCloseIconClickListener(view -> {
            removeTag(friendChip, friend);
        });

        chipGroup.addView(friendChip);
    }

    private void removeTag(Chip friendChip, Friend friend) {
        chipGroup.removeView(friendChip);
        putFriendBackInTheList(friend);
    }

    private void putFriendBackInTheList(Friend friend) {
        friendsAdapter.putFriendBack(friend);
    }

    @Override
    public void onFriendSelected(Friend friend) {
        createChip(friend);
    }
}
