package com.hcip.team.three.echoes.utils.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.hcip.team.three.echoes.EchoesApplication;
import com.hcip.team.three.echoes.R;

import java.util.ArrayList;

public class AvailablePicturesAdapter extends BaseAdapter {

    private final Context context;
    private final EchoesApplication echoesApplication;

    private final ArrayList<Drawable> allAvailableImages;
    private final ArrayList<Drawable> selectedImages;
    private final ArrayList<Boolean> isSelected;

    @SuppressLint("UseCompatLoadingForDrawables")
    public AvailablePicturesAdapter(Context context, EchoesApplication echoesApplication) {
        this.context = context;
        this.echoesApplication = echoesApplication;

        isSelected = new ArrayList<>();
        selectedImages = new ArrayList<>();
        allAvailableImages = new ArrayList<>();

        allAvailableImages.add(context.getResources().getDrawable(R.drawable.img_available_18));
        allAvailableImages.add(context.getResources().getDrawable(R.drawable.img_available_1));
        allAvailableImages.add(context.getResources().getDrawable(R.drawable.img_available_2));
        allAvailableImages.add(context.getResources().getDrawable(R.drawable.img_available_3));
        allAvailableImages.add(context.getResources().getDrawable(R.drawable.img_available_4));
        allAvailableImages.add(context.getResources().getDrawable(R.drawable.img_available_7));
        allAvailableImages.add(context.getResources().getDrawable(R.drawable.img_available_8));
        allAvailableImages.add(context.getResources().getDrawable(R.drawable.img_available_9));
        allAvailableImages.add(context.getResources().getDrawable(R.drawable.img_available_10));
        allAvailableImages.add(context.getResources().getDrawable(R.drawable.img_available_11));
        allAvailableImages.add(context.getResources().getDrawable(R.drawable.img_available_12));
        allAvailableImages.add(context.getResources().getDrawable(R.drawable.img_available_13));
        allAvailableImages.add(context.getResources().getDrawable(R.drawable.img_available_14));
        allAvailableImages.add(context.getResources().getDrawable(R.drawable.img_available_15));
        allAvailableImages.add(context.getResources().getDrawable(R.drawable.img_available_16));
        allAvailableImages.add(context.getResources().getDrawable(R.drawable.img_available_19));
        allAvailableImages.add(context.getResources().getDrawable(R.drawable.img_available_20));
        allAvailableImages.add(context.getResources().getDrawable(R.drawable.img_available_21));
        allAvailableImages.add(context.getResources().getDrawable(R.drawable.img_available_22));
        allAvailableImages.add(context.getResources().getDrawable(R.drawable.img_available_23));
        allAvailableImages.add(context.getResources().getDrawable(R.drawable.img_available_24));
        allAvailableImages.add(context.getResources().getDrawable(R.drawable.img_available_25));
        allAvailableImages.add(context.getResources().getDrawable(R.drawable.img_available_26));
        allAvailableImages.add(context.getResources().getDrawable(R.drawable.img_available_27));
        allAvailableImages.add(context.getResources().getDrawable(R.drawable.img_available_28));
        allAvailableImages.add(context.getResources().getDrawable(R.drawable.img_available_29));
        allAvailableImages.add(context.getResources().getDrawable(R.drawable.img_available_30));
        allAvailableImages.add(context.getResources().getDrawable(R.drawable.img_available_31));
        allAvailableImages.add(context.getResources().getDrawable(R.drawable.img_available_32));
        allAvailableImages.add(context.getResources().getDrawable(R.drawable.img_available_33));
        allAvailableImages.add(context.getResources().getDrawable(R.drawable.img_available_34));
        allAvailableImages.add(context.getResources().getDrawable(R.drawable.img_available_35));
        allAvailableImages.add(context.getResources().getDrawable(R.drawable.img_available_36));
        allAvailableImages.add(context.getResources().getDrawable(R.drawable.img_available_37));

        for (int i = 0; i < allAvailableImages.size(); i++) {
            isSelected.add(false);
        }
    }

    @Override
    public int getCount() {
        return allAvailableImages.size();
    }

    @Override
    public Object getItem(int i) {
        return allAvailableImages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View item = inflater.inflate(R.layout.custom_layout_picture_selection, parent, false);

        try {
            Drawable drawable = allAvailableImages.get(i);

            ImageView availableImage = item.findViewById(R.id.available_image);
            ImageView selectedCheck = item.findViewById(R.id.selected_check);

            if (isSelected.get(i)) {
                selectedVisuals(item);
            } else {
                unselectedVisuals(item);
            }

            availableImage.setImageDrawable(drawable);
            item.setOnClickListener(v -> {
                if (selectedCheck.getVisibility() == View.GONE) {
                    selectImage(i, item);
                } else {
                    unselectImage(i, item);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return item;
    }

    public ArrayList<Drawable> getSelectedImages() {
        selectedImages.clear();

        for (int i = 0; i < isSelected.size(); i++) {
            if (isSelected.get(i)) {
                selectedImages.add(allAvailableImages.get(i));
            }
        }

        return selectedImages;
    }

    private void selectImage(int i, View item) {
        selectedVisuals(item);
        isSelected.set(i, true);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void selectedVisuals(View item) {
        item.setBackground(context.getResources().getDrawable(R.drawable.drw_picture_background_selected));
        ImageView selectedCheck = item.findViewById(R.id.selected_check);
        selectedCheck.setVisibility(View.VISIBLE);
    }

    private void unselectImage(int i, View item) {
        unselectedVisuals(item);
        isSelected.set(i, false);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void unselectedVisuals(View item) {
        item.setBackground(context.getResources().getDrawable(R.drawable.drw_picture_background_unselected));
        ImageView selectedCheck = item.findViewById(R.id.selected_check);
        selectedCheck.setVisibility(View.GONE);
    }

}
