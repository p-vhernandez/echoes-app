package com.hcip.team.three.echoes.utils.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hcip.team.three.echoes.EchoesApplication;
import com.hcip.team.three.echoes.R;
import com.hcip.team.three.echoes.model.Echo;
import com.hcip.team.three.echoes.model.Friend;
import com.hcip.team.three.echoes.model.Mood;

import java.util.ArrayList;
import java.util.Objects;

public class EchoesAdapter extends BaseAdapter {

    private final EchoesApplication echoesApplication;

    private final Context context;
    private final ArrayList<Echo> allEchoes;

    public EchoesAdapter(Context context, EchoesApplication echoesApplication, ArrayList<Echo> allEchoes) {
        this.context = context;
        this.echoesApplication = echoesApplication;
        this.allEchoes = allEchoes;
    }

    @Override
    public int getCount() {
        return allEchoes.size();
    }

    @Override
    public Object getItem(int i) {
        return allEchoes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return allEchoes.get(i).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View row = inflater.inflate(R.layout.custom_layout_echo, parent, false);

        try {
            Echo echo = allEchoes.get(position);
            Mood mood = null;
            Friend creator;
            if (echo.getCreator() == 9) {
                creator = echoesApplication.getUser();
            } else {
                creator = echoesApplication.getFriends().get(echo.getCreator());
            }

            if (echo.isHasMood()) {
                mood = echoesApplication.getMoods().get(echo.getMood());
            }

            TextView echoTitle = row.findViewById(R.id.echo_title);
            TextView echoDate = row.findViewById(R.id.echo_date);

            ImageView creatorImage = row.findViewById(R.id.creator_image);
            ImageView echoSingleImage = row.findViewById(R.id.echo_single_image);
            ImageView echoMood = row.findViewById(R.id.mood_image);

            echoTitle.setText(echo.getTitle());
            echoDate.setText(echoesApplication.stringFromDate(echo.getDate()));

            creatorImage.setImageDrawable(echoesApplication.imageDecoder(creator.getB64ProfilePicture()));
            echoMood.setImageDrawable(echoesApplication.imageDecoder(Objects.requireNonNull(mood).getMoodImage()));

            if (echo.getB64Pictures().size() == 1) {
                echoSingleImage.setImageDrawable(echoesApplication.imageDecoder(echo.getB64Pictures().get(0)));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return row;
    }
}
