package com.hcip.team.three.echoes.utils.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hcip.team.three.echoes.EchoesApplication;
import com.hcip.team.three.echoes.R;
import com.hcip.team.three.echoes.fragments.creation.TagsFragment;
import com.hcip.team.three.echoes.model.Friend;
import com.hcip.team.three.echoes.utils.interfaces.FriendSelectedListener;

import java.util.ArrayList;

public class FriendsAdapter extends BaseAdapter {

    private final Context context;
    private final EchoesApplication echoesApplication;

    private final ArrayList<Friend> allFriends;
    private final ArrayList<Friend> selectedFriends;

    private final FriendSelectedListener friendSelectedListener;

    public FriendsAdapter(Context context, EchoesApplication echoesApplication, FriendSelectedListener friendSelectedListener) {
        this.context = context;
        this.echoesApplication = echoesApplication;
        this.friendSelectedListener = friendSelectedListener;

        this.allFriends = echoesApplication.getFriends();
        this.selectedFriends = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return allFriends.size();
    }

    @Override
    public Object getItem(int i) {
        return allFriends.get(i);
    }

    @Override
    public long getItemId(int i) {
        return allFriends.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View item = inflater.inflate(R.layout.custom_layout_friend, parent, false);

        try {
            ImageView imgFriend = item.findViewById(R.id.friend_image);
            TextView txtFriendName = item.findViewById(R.id.friend_name);

            imgFriend.setImageDrawable(echoesApplication.imageDecoder(allFriends.get(i).getB64ProfilePicture()));
            txtFriendName.setText(allFriends.get(i).getName());

            item.setOnClickListener(v -> addTag(i));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return item;
    }

    private void addTag(int i) {
        Friend selected = allFriends.get(i);
        Log.e("FRIEND POSITION", i + "");
        Log.e("FRIEND ID", selected.getId() + "");
        Log.e("END CHIP", "----------------------------");

        this.selectedFriends.add(selected);
        this.allFriends.remove(selected);

        this.notifyDataSetChanged();
        friendSelectedListener.onFriendSelected(selected);
    }

    public void putFriendBack(Friend friend) {
        allFriends.add(friend.getId(), friend);
        selectedFriends.remove(friend);

        this.notifyDataSetChanged();
    }

}
