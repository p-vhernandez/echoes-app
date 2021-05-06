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
import com.hcip.team.three.echoes.model.Mood;

import java.util.ArrayList;

public class MoodsAdapter extends BaseAdapter {

    private final Context context;
    private final EchoesApplication echoesApplication;

    private final ArrayList<Mood> allMoods;
    private ImageView selectedImage;
    private int selectedMood = -1;

    public MoodsAdapter(Context context, EchoesApplication echoesApplication) {
        this.context = context;
        this.echoesApplication = echoesApplication;

        this.allMoods = echoesApplication.getMoods();
    }

    @Override
    public int getCount() {
        return allMoods.size();
    }

    @Override
    public Object getItem(int i) {
        return allMoods.get(i);
    }

    @Override
    public long getItemId(int i) {
        return allMoods.get(i).getId();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public View getView(int i, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View item = inflater.inflate(R.layout.custom_layout_mood, parent, false);

        try {
            ImageView imgMood = item.findViewById(R.id.mood_image);
            imgMood.setBackground(context.getResources().getDrawable(R.drawable.drw_mood_unselected));
            imgMood.setImageDrawable(echoesApplication.imageDecoder(allMoods.get(i).getMoodImage()));
            imgMood.setScaleType(ImageView.ScaleType.FIT_XY);
            imgMood.setAdjustViewBounds(true);

            TextView txtMood = item.findViewById(R.id.mood_name);
            txtMood.setText(allMoods.get(i).getMoodName());
            imgMood.setOnClickListener(v -> selectMood(imgMood, i));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return item;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void selectMood(ImageView imgMood, int i) {
        if (selectedImage != null) {
            selectedImage.setBackground(context.getResources().getDrawable(R.drawable.drw_mood_unselected));
        }

        if (i == selectedMood) {
            imgMood.setBackground(context.getResources().getDrawable(R.drawable.drw_mood_unselected));
            selectedImage = null;
            selectedMood = -1;
            echoesApplication.getNewEcho().setHasMood(false);
        } else {
            imgMood.setBackground(context.getResources().getDrawable(R.drawable.drw_mood_selected));
            selectedImage = imgMood;
            selectedMood = i;
            echoesApplication.getNewEcho().setHasMood(true);
        }

        echoesApplication.saveEchoMood(selectedMood);
    }
}
